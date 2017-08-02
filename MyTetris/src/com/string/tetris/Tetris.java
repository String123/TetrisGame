package com.string.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Tetris extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 20;
	public static final int TWIDTH = 30;
	private static final int TIMESPAN = 10;

	private static final int START = 0;
	private static final int GAME = 1;
	private static final int PAUSE = 2;
	private static final int OVER = 3; // ��Ϸ״ָ̬�볣��

	private static final int SAVEOVER = 1;

	public static final int I = 0;
	public static final int J = 1;
	public static final int L = 2;
	public static final int O = 3;
	public static final int T = 4;
	public static final int S = 5;
	public static final int Z = 6;

	public static BufferedImage[] CELL = new BufferedImage[7];
	public static BufferedImage BACKGROUND;
	public static BufferedImage start;
	public static BufferedImage over;
	public static BufferedImage pause;
	public static BufferedImage NEXT;
	public static BufferedImage pressL;
	public static BufferedImage pressN;
	public static BufferedImage pressS;

	private Tetromino nTetrises;
	private Tetromino temp;
	private Tetromino nextT;
	private Cell[] tCells;
	private int times, stimes; // ʱ��������
	private int tTimes; // ��ת����
	private Integer score; // ����
	private int ip, sp, next;

	static { // ��̬����ط���ͼƬ�ͱ���ͼƬ
		try {
			CELL[I] = ImageIO.read(Tetris.class.getResource("I.png"));
			CELL[J] = ImageIO.read(Tetris.class.getResource("J.png"));
			CELL[L] = ImageIO.read(Tetris.class.getResource("L.png"));
			CELL[O] = ImageIO.read(Tetris.class.getResource("O.png"));
			CELL[T] = ImageIO.read(Tetris.class.getResource("T.png"));
			CELL[S] = ImageIO.read(Tetris.class.getResource("S.png"));
			CELL[Z] = ImageIO.read(Tetris.class.getResource("Z.png"));
			start = ImageIO.read(Tetris.class.getResource("start.png"));
			pause = ImageIO.read(Tetris.class.getResource("pause.png"));
			over = ImageIO.read(Tetris.class.getResource("gameover.gif"));
			NEXT = ImageIO.read(Tetris.class.getResource("next.png"));
			pressL = ImageIO.read(Tetris.class.getResource("pressL.png"));
			pressS = ImageIO.read(Tetris.class.getResource("pressS.png"));
			pressN = ImageIO.read(Tetris.class.getResource("pressN.png"));
			BACKGROUND = ImageIO.read(Tetris.class
					.getResource("background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) { // ��Ϸ������,������Ϸ���岢��ʼ��Ϸ����
		Tetris tetris = new Tetris();
		JFrame frame = new JFrame("TetrisGame");
		frame.setSize(WIDTH * TWIDTH + 200, HEIGHT * TWIDTH); // ���ô����С
		frame.add(tetris);
		frame.setLocationRelativeTo(null); // ���ô��������ʾ
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false); // ���ò��ɸı䴰���С
		frame.setVisible(true); // ���ô���ɼ�,�Զ�����paint����
		tetris.action(); // ��Ϸ��ʼ
	}

	@Override
	public void paint(Graphics g) { // �ع����ʹ���
		g.drawImage(BACKGROUND, 0, 0, WIDTH * TWIDTH + 200, HEIGHT * TWIDTH,
				null);
		// ����������
		g.setColor(Color.PINK);
		for (int i = 0; i < HEIGHT - 1; i++) {
			for (int j = 0; j < WIDTH; j++) {
				g.drawRect(j * TWIDTH, i * TWIDTH, TWIDTH - 1, TWIDTH - 1);
			}
		}
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		g.setFont(f);
		g.setColor(Color.WHITE);
		
		if (ip == START) {
			loadGame();
			paintTetris(g);
			g.drawImage(start, 0, 150, 300, 200, null);
			// g.drawImage(pressN,310,400,200,50,null);
			g.drawString("[�� N����ʼ����Ϸ]", 310, 400);
			g.drawString("[�� L����ȡ�浵]", 320, 450);
		} else {
			if (ip == GAME) {
				g.drawString("NEXT", 375, 100);
				g.drawString("[�� S�����д浵]", 320, 400);
			}
			if (sp == SAVEOVER) {
				g.drawString("�浵���!", 330, 450);
			}
			paintTetris(g); // ���Ʒ���
			paintNext(g);
			g.drawString("SCORE :" + score, 20, 30);
			if (ip == PAUSE) {
				g.drawImage(pause, -150, 0, 600, 600, null);
			} else if (ip == OVER) {
				g.drawImage(over, 50, 250, 200, 100, null);
				g.drawString("[�� N����ʼ����Ϸ]", 310, 400);
				g.drawString("[�� L����ȡ�浵]", 320, 450);
			}
		}
	}

	public void paintTetris(Graphics g) { // ��ӡ����
		for (Cell c : nTetrises.cells) {
			g.drawImage(CELL[c.image], c.x * TWIDTH, c.y * TWIDTH, TWIDTH,
					TWIDTH, null);
		}
		for (Cell c : tCells) {
			g.drawImage(CELL[c.image], c.x * TWIDTH, c.y * TWIDTH, TWIDTH,
					TWIDTH, null);
		}
	}

	public void paintNext(Graphics g) {
		for (Cell c : nextT.cells) {
			g.drawImage(CELL[c.image], c.x * TWIDTH, c.y * TWIDTH, TWIDTH,
					TWIDTH, null);
		}
	}

	public Tetromino makeTetrises(int type) { // ������ɶ�̬Ŀ�귽��
		int x, y;
		Tetromino tet = null;
		switch (type) {
		case I:
			x = (int) (Math.random() * (WIDTH - 3));
			y = -1;
			Tetromino i = new I(x, y);
			tet = i;
			break;
		case J:
			x = (int) (Math.random() * (WIDTH - 2));
			y = -2;
			Tetromino j = new J(x, y);
			tet = j;
			break;
		case L:
			x = (int) (Math.random() * (WIDTH - 1));
			y = -3;
			Tetromino l = new L(x, y);
			tet = l;
			break;
		case O:
			x = (int) (Math.random() * (WIDTH - 1));
			y = -2;
			Tetromino o = new O(x, y);
			tet = o;
			break;
		case T:
			x = (int) (Math.random() * (WIDTH - 2));
			y = -2;
			Tetromino t = new T(x, y);
			tet = t;
			break;
		case S:
			x = (int) (Math.random() * (WIDTH - 2));
			y = -1;
			Tetromino s = new S(x, y);
			tet = s;
			break;
		case Z:
			x = (int) (Math.random() * (WIDTH - 2));
			y = -2;
			Tetromino z = new Z(x, y);
			tet = z;
			break;
		}
		tTimes = 0; // ��ʼ�����ɵķ������ת����
		return tet;
	}

	public Tetromino makeTetrises(int type, int x, int y) { // ���ɹ̶�λ��Ŀ�귽��,��ҪΪ��ʾ��һ������
		Tetromino tet = null;
		switch (type) {
		case I:
			Tetromino i = new I(x, y);
			tet = i;
			break;
		case J:
			Tetromino j = new J(x, y);
			tet = j;
			break;
		case L:
			Tetromino l = new L(x, y);
			tet = l;
			break;
		case O:
			Tetromino o = new O(x, y);
			tet = o;
			break;
		case T:
			Tetromino t = new T(x, y);
			tet = t;
			break;
		case S:
			Tetromino s = new S(x, y);
			tet = s;
			break;
		case Z:
			Tetromino z = new Z(x, y);
			tet = z;
			break;
		}
		return tet;
	}

	public boolean infeedCheck(Tetromino t) { // ������ײ���
		boolean b = false;
		for (int i = 0; i < t.cells.length; i++) {
			if (t.cells[i].x < 0 || t.cells[i].x > Tetris.WIDTH - 1) {
				b = true;
				break;
			}
			for (int j = 0; j < tCells.length; j++) {
				if (t.cells[i].x == tCells[j].x && t.cells[i].y == tCells[j].y) {
					b = true;
					break;
				}
			}
		}
		return b;
	}

	public boolean touchCheck() { // ���׻򷽿�����Ӵ����
		boolean b = false;
		for (int i = 0; i < nTetrises.cells.length; i++) {
			if (nTetrises.cells[i].y == (HEIGHT - 2)) {
				b = true;
				break;
			}
		} // ����Ƿ񴥵�
		for (int i = 0; i < nTetrises.cells.length; i++) {
			for (int j = 0; j < tCells.length; j++) {
				if ((nTetrises.cells[i].y == (tCells[j].y - 1))
						&& (nTetrises.cells[i].x == (tCells[j].x))) {
					b = true;
					break;
				}
			}
		} // ����Ƿ��з������䷽����
		return b;
	}

	public void addCells(boolean b) { // �����׻��з��鴥���ķ�����뾲̬��������,���������ɶ�̬Ŀ�귽��
		int temp;
		if (b) {
			temp = tCells.length; // ��¼����ǰ�ľ�̬cell���鳤��
			tCells = Arrays.copyOf(tCells, tCells.length
					+ nTetrises.cells.length);
			System.arraycopy(nTetrises.cells, 0, tCells, temp,
					nTetrises.cells.length);
			nTetrises = makeTetrises(next);
			next = (int) (Math.random() * 7);
			nextT = makeTetrises(next, WIDTH + 1, 5);
		}
	}

	public void moveDown() { // ����ֱ������������������λ
		while (!touchCheck()) {
			nTetrises.step();
		}
	}

	public void clearCheck() { // ���м�Ⲣ����
		int[] yt = new int[HEIGHT]; // ÿһ�е�cell����
		for (int i = 0; i < yt.length; i++) {
			yt[i] = 0;
		} // ��ʼ��ÿ��cell����
		for (int i = 0; i < tCells.length; i++) {
			if (tCells[i].y > 0) {
				yt[tCells[i].y]++;
			}
		} // ÿ��cell��y�������һ��,��y���������е�cell������һ
		for (int i = 0; i < yt.length; i++) {
			if (yt[i] == WIDTH) { // ����ÿ���е�cell����,��������������������
				for (int j = 0; j < tCells.length; j++) {
					if (tCells[j].y == i) {
						tCells[j] = tCells[tCells.length - 1];
						tCells = Arrays.copyOf(tCells, tCells.length - 1);
						j--;
					}
				}
				// ���к��Ϸ�����������һ��
				for (int j = 0; j < tCells.length; j++) {
					if (tCells[j].y < i) {
						tCells[j].y++;
					}
				}
				// �Ϸ��е�cellͳ�������ƶ�һ��
				for (int j = i - 1; j >= 0; j--) {
					yt[j + 1] = yt[j];
				}
				score += 10; // ����һ�м�10��
			}
		}
	}

	public void overCheck() {
		for (int i = 0; i < tCells.length; i++) {
			if (tCells[i].y <= 0) {
				ip = OVER;
				break;
			}
		}
	}

	/**
	 * ��ʼ����Ϸ����
	 * 
	 * @throws Exception
	 */
	public void newData() {
		Tetris tetris = new Tetris();
		sp = 0; // �浵�����ʾ״ָ̬��
		temp = null; // Ŀ�귽�鸱��,������ת����
		times = 0; // ʱ����������
		ip = START; // ��Ϸ״ָ̬���ʼ��Ϊ��ʼ״̬
		next = (int) (Math.random() * 7);
		nTetrises = tetris.makeTetrises(next); // ��ʼ��Ŀ���ƶ�����
		next = (int) (Math.random() * 7);
		nextT = tetris.makeTetrises(next, WIDTH + 1, 5);
	}

	/**
	 * ��ȡ��Ϸ�ϴα���ĵײ㷽��״̬
	 */
	public void loadGame() {
		try {
			File f = new File("save.obj");
			if (!f.exists()) {
				f.createNewFile();
			}
			FileInputStream fis;
			fis = new FileInputStream("save.obj");
			if (fis.read() != -1) {
				ObjectInputStream ois = new ObjectInputStream(fis);
				score = (Integer) ois.readObject();
				tCells = (Cell[]) ois.readObject(); // �̶�cell��������
				ois.close();
			} else {
				tCells = new Cell[0];
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * �洢��Ϸ��ǰ�ĵײ㷽��״̬
	 */
	public void saveGame() {
		FileOutputStream fos;
		try {
			File f = new File("save.obj");
			if (!f.exists()) {
				f.createNewFile();
			}
			fos = new FileOutputStream("save.obj");
			fos.write(0);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(score);
			oos.writeObject(tCells);
			oos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void action() { // ��Ϸ��ʼ����
		newData();
		Timer t = new Timer();
		KeyAdapter key = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					nTetrises.moveLeft();
					if (infeedCheck(nTetrises)) {
						nTetrises.moveRight();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					nTetrises.moveRight();
					if (infeedCheck(nTetrises)) {
						nTetrises.moveLeft();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN && ip != OVER) {
					moveDown();
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					try {
						temp = (Tetromino) nTetrises.clone();
						temp.turnAround(tTimes % 4);
						if (!infeedCheck(temp)) {
							nTetrises.turnAround(tTimes++ % 4);
						}
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
				}
				// ��P������ͣ��Ϸ
				if (e.getKeyCode() == KeyEvent.VK_P) {
					if (ip == GAME) {
						ip = PAUSE;
					} else if (ip == PAUSE) {
						ip = GAME;
					}
				}
				// ��N���ɿ�ʼ����Ϸ
				if (e.getKeyCode() == KeyEvent.VK_N) {
					if (ip == START || ip == OVER) {
						tCells = new Cell[0];
						score=0;
						newData();
						ip = GAME;
					}
				}
				// ��L����ȡ�浵����ʼ��Ϸ
				if (e.getKeyCode() == KeyEvent.VK_L) {
					if (ip == START || ip == OVER) {
						loadGame();
						newData();
						ip = GAME;
					}
				}
				// ��S���ɴ洢��ǰ��Ϸ�����䷽���״̬,�����´δ���Ϸʱ�Զ���ȡ
				if (e.getKeyCode() == KeyEvent.VK_S && ip == GAME) {
					saveGame();
					sp = SAVEOVER;
				}
			}
		};
		this.addKeyListener(key);
		this.setFocusable(true);
		this.requestFocus();
		t.schedule(new TimerTask() {
			@Override
			public void run() {
				if (sp == SAVEOVER) {
					stimes++;
				}
				if (stimes == 200) {
					stimes = 0;
					sp = 0;
				}
				if (ip == GAME) {
					times++;
					if (times % 50 == 0 && !touchCheck()) {
						nTetrises.step();
					}
					if (times % 30 == 0) {
						addCells(touchCheck());
						clearCheck();
					}
					overCheck();
				}
				repaint();
			}
		}, TIMESPAN, TIMESPAN);
	}
}
