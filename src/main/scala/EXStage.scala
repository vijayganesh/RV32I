//import Chisel.switch
import chisel3._
import chisel3.util._

/**
 *
 * @author P.C. Vijay Ganesh
 * @todo Finalise the Opcode width
 * @todo Branch and Shift operations
 */
class EXStage (val datawidth : Int = 32, val idexereg : Int) extends Module{
val IO = IO(new Bundle() {
  val IDEXEReg = Input(UInt(idexereg.W)) // yet to finalise
  val Operand1 = Input(UInt(datawidth.W))
  val Operand2 = Input(UInt(datawidth.W))
  val ResultAlu = Output(UInt(datawidth.W))

})

  // from RegIDIE need to decode what to execute



}

class ALU (val datawidth : Int = 32) extends Module {

  val IO = IO(new Bundle() {
    val Opcode = Input(UInt(6.W)) // yet to finalise
    val Operand1 = Input(UInt(datawidth.W))
    val Operand2 = Input(UInt(datawidth.W))
    val ResultAlu = Output(UInt(datawidth.W))

  })
  switch(IO.Opcode) {
    is(0.U) {
      IO.ResultAlu := IO.Operand1 + IO.Operand2

    }
    is(1.U) {
      IO.ResultAlu := IO.Operand1 - IO.Operand2

    }
    is(2.U) {
      IO.ResultAlu := IO.Operand1 & IO.Operand2
    }

    is(3.U) {
      IO.ResultAlu := IO.Operand1 | IO.Operand2
    }
    is(4.U) {
      IO.ResultAlu := IO.Operand1 ^ IO.Operand2
    }
  }
}