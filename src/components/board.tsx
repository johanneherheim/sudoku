"use client";
import { useState } from "react";

const unsolvedBoard = [
  [0, 0, 7, 4, 9, 1, 6, 0, 5],
  [2, 0, 0, 0, 6, 0, 3, 0, 9],
  [0, 0, 0, 0, 0, 7, 0, 1, 0],
  [0, 5, 8, 6, 0, 0, 0, 0, 4],
  [0, 0, 3, 0, 0, 0, 0, 9, 0],
  [0, 0, 6, 2, 0, 0, 0, 1, 8],
  [9, 0, 4, 0, 7, 0, 0, 0, 2],
  [6, 7, 0, 8, 3, 0, 0, 0, 0],
  [8, 1, 0, 0, 4, 5, 0, 0, 0],
];

const solvedBoard = [
  [3, 8, 7, 4, 9, 1, 6, 2, 5],
  [2, 4, 1, 5, 6, 8, 3, 7, 9],
  [5, 6, 9, 3, 2, 7, 4, 1, 8],
  [7, 5, 8, 6, 1, 9, 2, 3, 4],
  [1, 2, 3, 7, 8, 4, 5, 9, 6],
  [4, 9, 6, 2, 5, 3, 1, 8, 7],
  [9, 3, 4, 1, 7, 6, 8, 5, 2],
  [6, 7, 5, 8, 3, 2, 9, 4, 1],
  [8, 1, 2, 9, 4, 5, 7, 6, 3],
];

export default function Board() {
  const [currentNumber, setCurrentNumber] = useState(1);
  const [board, setBoard] = useState(unsolvedBoard);

  const handleCellClick = (row: number, col: number) => {
    const newBoard = board.slice();
    newBoard[row][col] = currentNumber;
    setBoard(newBoard);
  };

  const handleCurrentNumber = (num: number) => {
    setCurrentNumber(num);
  };

  const getCellColor = (row: number, col: number) => {
    if ((row < 3 || row > 5) && (col < 3 || col > 5)) {
      return "bg-gray-200";
    } else if (row < 3 || row > 5 || col < 3 || col > 5) {
      return "bg-gray-100";
    } else {
      return "bg-white";
    }
  };

  return (
    <div>
      <p>trykk på tallet du vil plassere i en celle ⬇️</p>
      <div className="gap-2 mb-5 mt-2 flex items-center justify-between">
        {[0, 1, 2, 3, 4, 5, 6, 7, 8, 9].map((num) => (
          <button
            key={num}
            onClick={() => handleCurrentNumber(num)}
            className={`${
              num === currentNumber ? "bg-gray-500" : "bg-gray-300"
            } w-10 h-10 flex items-center justify-center`}
          >
            {num < 1 ? "❌" : num}
          </button>
        ))}
      </div>
      <div className="grid grid-cols-9 gap-2">
        {board.map((row, i) =>
          row.map((cell, j) => (
            <button
              onClick={() => handleCellClick(i, j)}
              key={`${i}-${j}`}
              className={`border h-12 w-12 flex items-center justify-center ${getCellColor(
                i,
                j
              )}`}
            >
              {cell === 0 ? "" : cell}
            </button>
          ))
        )}
      </div>
      {solvedBoard === board && <p>GRATULERER!! 🥳</p>}
    </div>
  );
}
