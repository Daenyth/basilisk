package basilisk

sealed trait Zone {
  type P <: ZonePositioning
  def positioning: P

  def id: ZoneId
  def schema: ZoneSchema

  assert(positioning == schema.positioning)
}

object Zone {
  type Aux[P0 <: ZonePositioning] = Zone { type P = P0 }

  // Convenience aliases
  type Ordered = Zone.Aux[ZonePositioning.Ordered.type]
  type Unordered = Zone.Aux[ZonePositioning.Unordered.type]
  type XYPoint = Zone.Aux[ZonePositioning.XYPoint.type]
}

class OrderedZone(val id: ZoneId, val schema: ZoneSchema, val members: Vector[Card]) extends Zone {
  type P = ZonePositioning.Ordered.type
  val positioning = ZonePositioning.Ordered
}

case class ZoneId(value: Int) extends AnyVal

case class ZoneSchema(
    name: String,
    visibility: ZoneVisibility,
    ownership: ZoneOwnership,
    positioning: ZonePositioning
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

// TODO might scrap all the type safe ordering logic - UnsupportedOperation errors may be good enough
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

sealed trait ZoneOwner

object ZoneOwner {
  case object Shared extends ZoneOwner
  case class Owned(player: basilisk.Player) extends ZoneOwner
}

sealed trait ZonePosition[ZO <: ZonePositioning]

object ZonePosition {
  case class Index(value: Int) extends ZonePosition[ZonePositioning.Ordered.type]
  case object Present extends ZonePosition[ZonePositioning.Unordered.type]
  case class Point(x: Int, y: Int) extends ZonePosition[ZonePositioning.XYPoint.type]
}
