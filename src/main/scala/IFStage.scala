//import chisel3.{Mem, UInt}
import chisel3._
import chisel3.util._
import chisel3.util.experimental.loadMemoryFromFile
import firrtl.annotations.MemoryLoadFileType

class IFStage(val memWidth : Int, val memDepth : Int) extends Module{
  // Default memWidth = 32, memDepth = 1024
 val io = IO(new Bundle() {

   val pcUpdate = Input(UInt(1.W))
   val Instrucion = Output(UInt(memWidth.W))




 });

  //val PC = RegInit(0.U(UInt(memWidth.W)))
  val PC = RegInit(0.U((memDepth).W))
  //val programMemory = Mem(memDepth,UInt(memWidth.W))
  val programMemory = Mem(memDepth,UInt(memWidth.W))

  // Program Counter Logic
  // Assume that in every clock we need to fetch the opcode
  when(io.pcUpdate === 1.U)
  {
    // Need to update PC from Branch value
    PC := PC
  }
    .otherwise{
      PC := PC + 4.U
    }

  val l0 = PC
  val l1 = PC+1.U
  val l2 = PC + 2.U
  val l3 = PC + 3.U

 // load the ROM values from file ROM.
  loadMemoryFromFile(programMemory, "scala/ROM.hex", MemoryLoadFileType.Hex)
 // Loaded the Instrction values
  io.Instrucion := Cat(programMemory(l3),programMemory(l2),programMemory(l1),programMemory(l0))
  printf("The PC = %d and Instruction is 0x%x",PC,io.Instrucion)



}
