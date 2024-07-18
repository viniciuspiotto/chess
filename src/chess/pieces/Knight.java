package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "H";
    }

    private boolean canMove(Position pos) {
        ChessPiece p = (ChessPiece)getBoard().piece(pos);
        if (p == null) {
            return true;
        }
        return p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCols()];

        Position p = new Position(0,0);

        // above
        p.setValues(position.getRow() - 2, position.getCol() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // below
        p.setValues(position.getRow() - 2, position.getCol() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // left
        p.setValues(position.getRow() - 1, position.getCol() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // right
        p.setValues(position.getRow() + 1, position.getCol() - 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // nw
        p.setValues(position.getRow() + 2, position.getCol() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // ne
        p.setValues(position.getRow() + 2, position.getCol() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // sw
        p.setValues(position.getRow() - 1, position.getCol() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }
        // se
        p.setValues(position.getRow() + 1, position.getCol() + 2);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getCol()] = true;
        }

        return mat;
    }
}
