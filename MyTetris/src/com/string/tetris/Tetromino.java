package com.string.tetris;

class I extends Tetromino {

	public I(int x, int y) {
		type = Tetris.I;
		// cells=Arrays.copyOf(cells, cells.length+1);
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x + 2, y);
		cells[3] = new Cell(x + 3, y);
		// cells[4]= new Cell(x+4,y);
		cells[0].image = Tetris.I;
		cells[1].image = Tetris.I;
		cells[2].image = Tetris.I;
		cells[3].image = Tetris.I;
	}

	@Override
	public void turnAround(int t) {
		switch (t) {
		case 0:
		case 2:
			cells[0].x++;
			cells[0].y -= 2;
			cells[1].y--;
			cells[2].x--;
			cells[3].x -= 2;
			cells[3].y++;
			break;
		case 1:
		case 3:
			cells[0].x--;
			cells[0].y += 2;
			cells[1].y++;
			cells[2].x++;
			cells[3].x += 2;
			cells[3].y--;
			break;
		}
	}

	@Override
	public void turnAroundB(int t) {
		switch (t) {
		case 0:
		case 2:
			cells[0].x--;
			cells[0].y += 2;
			cells[1].y++;
			cells[2].x++;
			cells[3].x += 2;
			cells[3].y--;
			break;
		case 1:
		case 3:
			cells[0].x++;
			cells[0].y -= 2;
			cells[1].y--;
			cells[2].x--;
			cells[3].x -= 2;
			cells[3].y++;
			break;
		}

	}

}

class J extends Tetromino {

	public J(int x, int y) {
		type = Tetris.J;
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x + 2, y);
		cells[3] = new Cell(x + 2, y + 1);

		cells[0].image = Tetris.J;
		cells[1].image = Tetris.J;
		cells[2].image = Tetris.J;
		cells[3].image = Tetris.J;
	}

	@Override
	public void turnAround(int t) {
		switch (t) {
		case 0:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y++;
			cells[2].y += 2;
			cells[3].x--;
			cells[3].y++;
			break;
		case 1:
			cells[0].y += 2;
			cells[1].x--;
			cells[1].y++;
			cells[2].x -= 2;
			cells[3].x--;
			cells[3].y--;
			break;
		case 2:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y--;
			cells[2].y -= 2;
			cells[3].x++;
			cells[3].y--;
			break;
		case 3:
			cells[0].y -= 2;
			cells[1].x++;
			cells[1].y--;
			cells[2].x += 2;
			cells[3].x++;
			cells[3].y++;
		}
	}

	@Override
	public void turnAroundB(int t) {
		switch (t) {
		case 0:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y--;
			cells[2].y -= 2;
			cells[3].x++;
			cells[3].y--;
			break;
		case 1:
			cells[0].y -= 2;
			cells[1].x++;
			cells[1].y--;
			cells[2].x += 2;
			cells[3].x++;
			cells[3].y++;
			break;
		case 2:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y++;
			cells[2].y += 2;
			cells[3].x--;
			cells[3].y++;
			break;
		case 3:
			cells[0].y += 2;
			cells[1].x--;
			cells[1].y++;
			cells[2].x -= 2;
			cells[3].x--;
			cells[3].y--;
		}
	}
}

class L extends Tetromino {

	public L(int x, int y) {
		type = Tetris.L;
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x, y + 1);
		cells[2] = new Cell(x, y + 2);
		cells[3] = new Cell(x + 1, y + 2);

		cells[0].image = Tetris.L;
		cells[1].image = Tetris.L;
		cells[2].image = Tetris.L;
		cells[3].image = Tetris.L;
	}

	@Override
	public void turnAround(int t) {
		switch (t) {
		case 0:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y--;
			cells[2].y -= 2;
			cells[3].x--;
			cells[3].y--;
			break;
		case 1:
			cells[0].y += 2;
			cells[1].x++;
			cells[1].y++;
			cells[2].x += 2;
			cells[3].x++;
			cells[3].y--;
			break;
		case 2:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y++;
			cells[2].y += 2;
			cells[3].x++;
			cells[3].y++;
			break;
		case 3:
			cells[0].y -= 2;
			cells[1].x--;
			cells[1].y--;
			cells[2].x -= 2;
			cells[3].x--;
			cells[3].y++;
			break;
		}
	}

	@Override
	public void turnAroundB(int t) {
		switch (t) {
		case 0:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y++;
			cells[2].y += 2;
			cells[3].x++;
			cells[3].y++;
			break;
		case 1:
			cells[0].y -= 2;
			cells[1].x--;
			cells[1].y--;
			cells[2].x -= 2;
			cells[3].x--;
			cells[3].y++;
			break;
		case 2:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y--;
			cells[2].y -= 2;
			cells[3].x--;
			cells[3].y--;
			break;
		case 3:
			cells[0].y += 2;
			cells[1].x++;
			cells[1].y++;
			cells[2].x += 2;
			cells[3].x++;
			cells[3].y--;
			break;
		}

	}
}

class O extends Tetromino {

	public O(int x, int y) {
		type = Tetris.O;
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x, y + 1);
		cells[3] = new Cell(x + 1, y + 1);

		cells[0].image = Tetris.O;
		cells[1].image = Tetris.O;
		cells[2].image = Tetris.O;
		cells[3].image = Tetris.O;
	}

	@Override
	public void turnAround(int t) {
	}

	@Override
	public void turnAroundB(int t) {
	}
}

class S extends Tetromino {

	public S(int x, int y) {
		type = Tetris.S;
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x + 1, y - 1);
		cells[3] = new Cell(x + 2, y - 1);

		cells[0].image = Tetris.S;
		cells[1].image = Tetris.S;
		cells[2].image = Tetris.S;
		cells[3].image = Tetris.S;
	}

	@Override
	public void turnAround(int t) {
		switch (t) {
		case 0:
		case 2:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y--;
			cells[3].x--;
			cells[3].y--;
			break;
		case 1:
		case 3:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y++;
			cells[3].x++;
			cells[3].y++;
		}
	}

	@Override
	public void turnAroundB(int t) {
		switch (t) {
		case 0:
		case 2:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y++;
			cells[3].x++;
			cells[3].y++;
			break;
		case 1:
		case 3:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y--;
			cells[3].x--;
			cells[3].y--;
		}

	}
}

class T extends Tetromino {

	public T(int x, int y) {
		type = Tetris.T;
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x + 2, y);
		cells[3] = new Cell(x + 1, y + 1);

		cells[0].image = Tetris.T;
		cells[1].image = Tetris.T;
		cells[2].image = Tetris.T;
		cells[3].image = Tetris.T;
	}

	@Override
	public void turnAround(int t) {
		switch (t) {
		case 0:
			cells[0].x++;
			cells[0].y--;
			cells[2].x--;
			cells[2].y++;
			cells[3].x--;
			cells[3].y--;
			break;
		case 1:
			cells[0].x++;
			cells[0].y++;
			cells[2].x--;
			cells[2].y--;
			cells[3].x++;
			cells[3].y--;
			break;
		case 2:
			cells[0].x--;
			cells[0].y++;
			cells[2].x++;
			cells[2].y--;
			cells[3].x++;
			cells[3].y++;
			break;
		case 3:
			cells[0].x--;
			cells[0].y--;
			cells[2].x++;
			cells[2].y++;
			cells[3].x--;
			cells[3].y++;
			break;
		}
	}

	@Override
	public void turnAroundB(int t) {
		switch (t) {
		case 0:
			cells[0].x--;
			cells[0].y++;
			cells[2].x++;
			cells[2].y--;
			cells[3].x++;
			cells[3].y++;
			break;
		case 1:
			cells[0].x--;
			cells[0].y--;
			cells[2].x++;
			cells[2].y++;
			cells[3].x--;
			cells[3].y++;
			break;
		case 2:
			cells[0].x++;
			cells[0].y--;
			cells[2].x--;
			cells[2].y++;
			cells[3].x--;
			cells[3].y--;
			break;
		case 3:
			cells[0].x++;
			cells[0].y++;
			cells[2].x--;
			cells[2].y--;
			cells[3].x++;
			cells[3].y--;
			break;
		}
	}
}

class Z extends Tetromino {

	public Z(int x, int y) {
		type = Tetris.Z;
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x + 1, y + 1);
		cells[3] = new Cell(x + 2, y + 1);

		cells[0].image = Tetris.Z;
		cells[1].image = Tetris.Z;
		cells[2].image = Tetris.Z;
		cells[3].image = Tetris.Z;
	}

	@Override
	public void turnAround(int t) {
		switch (t) {
		case 0:
		case 2:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y++;
			cells[3].x--;
			cells[3].y++;
			break;
		case 1:
		case 3:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y--;
			cells[3].x++;
			cells[3].y--;
		}
	}

	@Override
	public void turnAroundB(int t) {
		switch (t) {
		case 0:
		case 2:
			cells[0].x -= 2;
			cells[1].x--;
			cells[1].y--;
			cells[3].x++;
			cells[3].y--;
			break;
		case 1:
		case 3:
			cells[0].x += 2;
			cells[1].x++;
			cells[1].y++;
			cells[3].x--;
			cells[3].y++;
		}
	}
}

public abstract class Tetromino implements Cloneable {
	protected Cell[] cells = null; // 定义方块的格子对象
	protected int type;

	/**
	 * 重写clone方法,将内部地址也进行clone
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Tetromino newT = (Tetromino) super.clone();
		newT.cells = cells.clone();
		for (int i = 0; i < cells.length; i++) {
			newT.cells[i] = (Cell) cells[i].clone();
		}
		return newT;
	}

	/**
	 * 无参构造初始化方块的4个cell格子
	 */
	public Tetromino() {
		cells = new Cell[4];
	}

	/**
	 * 方块整体左移一次
	 */
	public void moveLeft() {
		boolean b = true;
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].x - 1 < 0)
				b = false;
		}
		if (b) {
			for (int i = 0; i < cells.length; i++) {
				cells[i].x--;
			}
		}
	}

	/**
	 * 方块整体右移一次
	 */
	public void moveRight() {
		boolean b = true;
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].x + 2 > Tetris.WIDTH)
				b = false;
		}
		if (b) {
			for (int i = 0; i < cells.length; i++) {
				cells[i].x++;
			}
		}
	}

	/**
	 * 方块整体下落一次
	 */
	public void step() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].y++;
		}
	}

	/**
	 * 方块整体顺时针旋转一次 由于每种方块的旋转方式不同,设置为抽象类,由子类自行重写
	 * 
	 * @param t
	 *            旋转次数指针
	 */
	public abstract void turnAround(int t);

	/**
	 * 方块整体逆时针旋转
	 * 
	 * @param t
	 *            旋转次数指针
	 */
	public abstract void turnAroundB(int t);

}
