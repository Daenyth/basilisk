package basilisk

case class Zone(
    name: String,
    visibility: ZoneVisibility,
    ownership: ZoneOwnership,
    positions: ZonePositioning
)

sealed trait ZoneVisibility

object ZoneVisibility {

  /** Everyone in a game can see it */
  case object Public extends ZoneVisibility

  /** The owner of the zone can see it */
  case object Private extends ZoneVisibility

  /** No one can see it */
  case object Hidden extends ZoneVisibility
}

sealed trait ZoneOwnership

object ZoneOwnership {

  /** Each player gets a copy of this zone, owned by them */
  case object EachPlayer extends ZoneOwnership

  /** There is one copy of this zone, and each player has access to it */
  case object Shared extends ZoneOwnership
}

sealed trait ZonePositioning

object ZonePositioning {

  /** A zone that supports ordering, like a deck of cards */
  case object Ordered extends ZonePositioning

  /** A zone where cards have no order: they have no positional relationship between each other. For example, a player's hand */
  case object Unordered extends ZonePositioning

  /** A coordinate layout where cards may have relative locations to each other, like cards on a table
    *
    * In this system, the axis unit is "card"
    */
  case object XYPoint extends ZonePositioning

}
