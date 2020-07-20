package basilisk
package schema

import cats.data.NonEmptyList

final case class GameSchema(attrs: Map[CardType, NonEmptyList[Attr]], zones: List[ZoneSchema])
