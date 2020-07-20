package basilisk
package poker

import cats.data.NonEmptyVector
import basilisk.schema.GameSchema
import cats.data.NonEmptyList
import basilisk.engine._

object FiveCardDraw { game =>

  val deck =
    ZoneSchema("Deck", ZoneVisibility.Hidden, ZoneOwnership.Shared, ZonePositioning.Ordered)

  val hand =
    ZoneSchema("Hand", ZoneVisibility.Private, ZoneOwnership.EachPlayer, ZonePositioning.Unordered)

  val discard =
    ZoneSchema("Discard", ZoneVisibility.Public, ZoneOwnership.Shared, ZonePositioning.Unordered)

  val zoneSchemas = List(deck, hand, discard)

  val suite =
    Attr("Suite", AttrType.Enum(NonEmptyVector.of("Hearts", "Clubs", "Diamonds", "Spades")))

  val rank = Attr(
    "Rank",
    AttrType.Enum(
      NonEmptyVector
        .of("Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King")))

  val cardType = CardType("Card", Nil)

  val gameSchema = GameSchema(Map(cardType -> NonEmptyList.of(rank, suite)), zoneSchemas)

  val allCards = for {
    suite <- suite.attrType.attrValues
    rank <- rank.attrType.attrValues
    uid = s"${rank.value(0)}${suite.value(0).toLower}}"
    name = s"$rank of $suite"
  } yield CardSchema(uid, name, cardType, Map(game.suite -> suite, game.rank -> rank))

  object engine {

    def newGame(numPlayers: Int): GameState = {
      require(numPlayers > 1) // TODO

      val players =
        fs2.Stream.range(1, numPlayers + 1).map(n => Player(s"Player $n")).compile.toList
      val discardState = ZoneState(discard, ZoneOwner.Shared, cards = List.empty)
      val handStates =
        fs2.Stream
          .emits(players)
          .map { player =>
            ZoneState(hand, ZoneOwner.Owned(player), cards = List.empty)
          }
          .compile
          .toList
      val deckState = ZoneState(
        deck,
        ZoneOwner.Shared,
        cards = fs2.Stream
          .emits(allCards.toVector)
          .zipWithIndex
          .map {
            case (schema, n) =>
              Card(CardId(n.toInt), schema)
          }
          .compile
          .toList
      )
      GameState(gameSchema, players, Vector(deckState, discardState) ++ handStates)
    }
  }
}
