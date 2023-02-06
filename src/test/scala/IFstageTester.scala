import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest.matchers.must.Matchers

import org.scalatest.flatspec.AnyFlatSpec

class IFstageTester extends AnyFlatSpec with ChiselScalatestTester with Matchers{
  behavior of "IF stage tester"
  "Load hex values " should "Load the Hex data and display" in {
    test(new IFStage(memWidth = 7,memDepth = 16)){
      c=>
        c.io.pcUpdate.poke(0.U)
        for (i <- 0 to 3) {
          c.clock.step(cycles = 1)
          println("The Instruction is 0x", c.io.Instrucion.peek().litValue.toString(16))
        }

    }

  }

}
