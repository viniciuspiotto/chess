package boardgame;

public class Board {
    private int rows;
    private int cols;
    private Piece[][] pieces;

    public Board(int rows, int cols) {
        if (rows < 1 || cols < 1) {
            throw new BoardException("Error creating board: rows and cols must be greater than 0");
        }
        this.rows = rows;
        this.cols = cols;
        this.pieces = new Piece[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Piece piece(int row, int col) {
        if (!positionExists(row, col)) {
            throw new BoardException("Error creating piece: position does not exist");
        }
        return pieces[row][col];
    }

    public Piece piece(Position pos) {
        if (!positionExists(pos)) {
            throw new BoardException("Error creating piece: position does not exist");
        }
        return pieces[pos.getRow()][pos.getCol()];
    }

    public void placePiece(Piece piece, Position pos) {
        if (thereIsPiece(pos)) {
            throw new BoardException("Error creating piece: there is already a piece in position " + pos);
        }
        pieces[pos.getRow()][pos.getCol()] = piece;
        piece.position = pos;
    }

    private boolean positionExists(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public boolean positionExists(Position pos) {
        return positionExists(pos.getRow(), pos.getCol());
    }

    public Piece removePiece(Position pos) {
        if (!positionExists(pos)) {
            throw new BoardException("Error remove piece: position does not exist");
        }
        if (piece(pos) == null) {
            return null;
        }
        Piece aux = piece(pos);
        aux.position = null;
        pieces[pos.getRow()][pos.getCol()] = null;
        return aux;
    }

    public boolean thereIsPiece(Position pos) {
        if (!positionExists(pos)) {
            throw new BoardException("Position does not exist");
        }
        return piece(pos) != null;
    }
}
