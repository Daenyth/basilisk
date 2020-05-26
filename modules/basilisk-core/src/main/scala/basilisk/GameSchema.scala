package basilisk

import org.w3c.dom.Attr

final case class GameSchema(attrs: Map[CardType, Attr], zones: List[Zone])
