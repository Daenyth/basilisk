package basilisk
package engine

import basilisk.schema.GameSchema

case class GameState(
    schema: GameSchema,
    players: List[Player],
    zones: List[ZoneState]
)

case class ZoneState(
    id: ZoneId,
    zone: Zone,
    owner: Player,
    cards: List[CardState]
)

case class CardState(card: Card, position: ZonePosition[_ <: ZonePositioning])
