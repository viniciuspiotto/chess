package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import boardgame.Piece;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {
    private final Board board;
    private int turn;
    private Color currentPlayer;

    private final List<ChessPiece> piecesOnTheBoard = new ArrayList<>();
    private final List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.RED;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getCols()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private void validateSourcePosition(Position pos) {
        if (!board.thereIsPiece(pos)) {
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece)board.piece(pos)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(pos).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible move on source position");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("There is no possible move on target position");
        }
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position source = sourcePosition.toPosition();
        validateSourcePosition(source);
        return board.piece(source).possibleMoves();
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = (ChessPiece) board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove((ChessPiece)capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.RED) ? Color.BLUE : Color.RED;
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Color.RED));
        placeNewPiece('e', 1, new King(board, Color.RED));
        placeNewPiece('h', 1, new Rook(board, Color.RED));

        placeNewPiece('a', 8, new Rook(board, Color.BLUE));
        placeNewPiece('e', 8, new King(board, Color.BLUE));
        placeNewPiece('h', 8, new Rook(board, Color.BLUE));
    }
}
