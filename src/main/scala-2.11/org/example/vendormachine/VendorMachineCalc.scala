package org.example.vendormachine

object VendorMachineCalc {

  /**
   * @return a Seq of used coins (biggest coins prioritized)
   *         or None if can't process (means return the giver whatever he gave)
   */
  def countTheMostOptimalChange(money: Int, availCoins: Seq[Int]): Option[Seq[Int]] = {
    countTheMostOptimalChange(money, availCoins.sorted.reverse, Nil)
  }
  
  private def countTheMostOptimalChange(money: Int, availCoins: Seq[Int], usedCoins: Seq[Int]): Option[Seq[Int]] = {
    if (money == 0) Some(usedCoins)
    else if (availCoins.isEmpty || money < 0) None
    else {
      countTheMostOptimalChange(money - availCoins.head, availCoins.tail, usedCoins :+ availCoins.head) match {
        case None => countTheMostOptimalChange(money, availCoins.tail, usedCoins) // the head coin is no help => try without it
        case change => change
      }
    }
  }

  /**
   * @return a Set of possible changes
   */
  def countPossibleChanges(money: Int, availCoins: Seq[Int]): Set[Seq[Int]] = {
    countPossibleChanges(money, Nil, availCoins).map(_.sorted.reverse).toSet // 'sorted.toSet' used to return unique changes only
  }

  private def countPossibleChanges(money: Int, usedCoins: Seq[Int], availCoins: Seq[Int]): Seq[Seq[Int]] = {
    if (money == usedCoins.sum) Seq(usedCoins)
    else if (availCoins.isEmpty) Seq()
    else countPossibleChanges(money, usedCoins :+ availCoins.head, availCoins.tail) ++ countPossibleChanges(money, usedCoins, availCoins.tail)
  }
}
