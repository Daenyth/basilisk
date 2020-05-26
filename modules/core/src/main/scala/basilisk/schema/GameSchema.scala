package basilisk
package schema

final case class GameSchema(attrs: Map[CardType, Attr], zones: List[Zone])
