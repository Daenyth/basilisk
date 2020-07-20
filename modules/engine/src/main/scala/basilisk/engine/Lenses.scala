package basilisk.engine

import monocle.macros.GenLens
import monocle.Lens

object Lenses {
  val gameZone: Lens[GameState, Vector[ZoneState]] = GenLens[GameState](_.zones)
  val zoneCards = GenLens[ZoneState](_.cards)
}
