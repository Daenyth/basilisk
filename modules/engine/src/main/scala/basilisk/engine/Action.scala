package basilisk.engine

import io.github.timwspence.cats.stm.STM
import io.github.timwspence.cats.stm.TVar

trait Action {
  def run(game: TVar[GameState]): STM[Unit]
}

object Action {

  def apply(f: TVar[GameState] => STM[Unit]): Action = new Action {
    def run(game: TVar[GameState]): STM[Unit] = f(game)
  }
}
