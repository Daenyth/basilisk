package basilisk

case class Zone(name: String, visibility: ZoneVisibility, owner: ZoneOwner, ordered: Boolean)

sealed trait ZoneVisibility

object ZoneVisibility {

  /** Everyone in a game can see it */
  case object Public extends ZoneVisibility

  /** The owner of the zone can see it */
  case object Private extends ZoneVisibility

  /** No one can see it */
  case object Hidden extends ZoneVisibility
}

case class ZoneOwner(player: Player)
