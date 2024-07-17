package chess;

import boardgame.Piece;
import boardgame.Board;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
    private final Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    protected boolean isThereOpponentPiece(Position pos) {
        ChessPiece p = (ChessPiece) getBoard().piece(pos);
        return p != null && p.getColor() != getColor();
    }

    public ChessPosition getChessPosition() {
        return ChessPosition.fromPosition(position);
    }
}
