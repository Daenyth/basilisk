package basilisk

import cats.data.NonEmptyList

case class Player(name: String)

case class Team(name: String, players: NonEmptyList[Player])
