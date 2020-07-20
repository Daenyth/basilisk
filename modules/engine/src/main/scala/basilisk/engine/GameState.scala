package basilisk
package engine

import basilisk.schema.GameSchema

case class GameState(
    schema: GameSchema,
    players: List[Player],
    zones: Vector[ZoneState] // TODO maybe should be Map[ZoneId, ZoneState] ?
)

case class ZoneState(
    // id: ZoneId,
    zone: ZoneSchema,
    owner: ZoneOwner,
    cards: List[Card]
)

// case class CardState(card: Card, position: ZonePosition[_ <: ZonePositioning])
