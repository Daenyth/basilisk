package basilisk
package data

object Builtins {

  val Hand =
    ZoneSchema("Hand", ZoneVisibility.Private, ZoneOwnership.EachPlayer, ZonePositioning.Unordered)

  val Deck =
    ZoneSchema("Deck", ZoneVisibility.Hidden, ZoneOwnership.EachPlayer, ZonePositioning.Ordered)

  val Exile =
    ZoneSchema("Exile", ZoneVisibility.Public, ZoneOwnership.EachPlayer, ZonePositioning.Unordered)

  val Table =
    ZoneSchema("Table", ZoneVisibility.Public, ZoneOwnership.EachPlayer, ZonePositioning.XYPoint)

  val SharedDeck =
    ZoneSchema("SharedDeck", ZoneVisibility.Hidden, ZoneOwnership.Shared, ZonePositioning.Ordered)

  val SharedTable =
    ZoneSchema("SharedTable", ZoneVisibility.Public, ZoneOwnership.Shared, ZonePositioning.XYPoint)
}
