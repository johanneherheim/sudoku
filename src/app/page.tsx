import Board from "@/components/board";

export default function Page() {
  return (
    <div className="h-screen w-screen flex flex-col items-center justify-center">
      <h1 className="mb-10">Sudoku</h1>
      <div>
        <Board />
      </div>
    </div>
  );
}
