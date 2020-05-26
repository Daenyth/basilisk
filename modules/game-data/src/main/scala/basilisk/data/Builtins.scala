package basilisk
package data

object Builtins {

  val Hand =
    Zone("Hand", ZoneVisibility.Private, ZoneOwnership.EachPlayer, ZonePositioning.Unordered)

  val Deck =
    Zone("Deck", ZoneVisibility.Hidden, ZoneOwnership.EachPlayer, ZonePositioning.Ordered)

  val Exile =
    Zone("Exile", ZoneVisibility.Public, ZoneOwnership.EachPlayer, ZonePositioning.Unordered)

  val Table =
    Zone("Table", ZoneVisibility.Public, ZoneOwnership.EachPlayer, ZonePositioning.XYPoint)

  val SharedDeck =
    Zone("SharedDeck", ZoneVisibility.Hidden, ZoneOwnership.Shared, ZonePositioning.Ordered)

  val SharedTable =
    Zone("SharedTable", ZoneVisibility.Public, ZoneOwnership.Shared, ZonePositioning.XYPoint)
}
