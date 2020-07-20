package basilisk.poker

import basilisk.engine.GameState

class PokerGameSpec extends munit.FunSuite {
  test("Drawing a hand") {
    val game = FiveCardDraw.engine.newGame(2)
    assertNotEquals(game, null: GameState)

  }
}
