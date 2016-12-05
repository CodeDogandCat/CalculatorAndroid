package com.lilei.calculator;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.os.Build;







import java.text.DecimalFormat;
import java.util.StringTokenizer;

import com.lilei.calculator.*;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	// �������
	private Button[] btn = new Button[10];// 0~9ʮ������
	private EditText input;// ������ʾ������
	private TextView mem, _drg, tip;
	private Button div, mul, sub, add, equal, sin, cos, tan, log, ln, sqrt,
			square, factorial, bksp, left, right, dot, exit, drg, mc, c;
	public String str_old;
	public String str_new;
	public boolean vbegin = true;// �������룬trueΪ�������룬falseΪ��������
	public boolean drg_flag = true;// trueΪ�Ƕȣ�falseΪ����
	public double pi = 4 * Math.atan(1);// ��ֵ
	public boolean tip_lock = true;// trueΪ��ȷ�����Լ������룬false������������
	public boolean equals_flag = true;// �Ƿ��ڰ���=֮�����룬trueΪ֮ǰ��falseΪ֮��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//�������
		setContentView(R.layout.activity_main);
		InitWigdet();
		AllWigdetListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * ��ʼ�����е����
	 */
	private void InitWigdet() {
		// ��ȡ����Ԫ��
		input = (EditText) findViewById(R.id.input);
		mem = (TextView) findViewById(R.id.mem);
		tip = (TextView) findViewById(R.id.tip);
		_drg = (TextView) findViewById(R.id._drg);
		btn[0] = (Button) findViewById(R.id.zero);
		btn[1] = (Button) findViewById(R.id.one);
		btn[2] = (Button) findViewById(R.id.two);
		btn[3] = (Button) findViewById(R.id.three);
		btn[4] = (Button) findViewById(R.id.four);
		btn[5] = (Button) findViewById(R.id.five);
		btn[6] = (Button) findViewById(R.id.six);
		btn[7] = (Button) findViewById(R.id.seven);
		btn[8] = (Button) findViewById(R.id.eight);
		btn[9] = (Button) findViewById(R.id.nine);
		div = (Button) findViewById(R.id.divide);
		mul = (Button) findViewById(R.id.mul);
		sub = (Button) findViewById(R.id.sub);
		add = (Button) findViewById(R.id.add);
		equal = (Button) findViewById(R.id.equal);
		sin = (Button) findViewById(R.id.sin);
		cos = (Button) findViewById(R.id.cos);
		tan = (Button) findViewById(R.id.tan);
		log = (Button) findViewById(R.id.log);
		ln = (Button) findViewById(R.id.ln);
		sqrt = (Button) findViewById(R.id.sqrt);
		square = (Button) findViewById(R.id.square);
		factorial = (Button) findViewById(R.id.factorial);
		bksp = (Button) findViewById(R.id.bksp);
		left = (Button) findViewById(R.id.left);
		right = (Button) findViewById(R.id.right);
		dot = (Button) findViewById(R.id.dot);
		exit = (Button) findViewById(R.id.exit);
		drg = (Button) findViewById(R.id.drg);
		mc = (Button) findViewById(R.id.mc);
		c = (Button) findViewById(R.id.c);
	}

	/**
	 * Ϊ���а����󶨼�����
	 */
	private void AllWigdetListener() {
		// ���ּ�
		for (int i = 0; i < 10; i++) {
			btn[i].setOnClickListener(actionPerformed);
		}
		// Ϊ+-x�µȰ����󶨼�����
		div.setOnClickListener(actionPerformed);
		mul.setOnClickListener(actionPerformed);
		sub.setOnClickListener(actionPerformed);
		add.setOnClickListener(actionPerformed);
		equal.setOnClickListener(actionPerformed);
		sin.setOnClickListener(actionPerformed);
		cos.setOnClickListener(actionPerformed);
		tan.setOnClickListener(actionPerformed);
		log.setOnClickListener(actionPerformed);
		ln.setOnClickListener(actionPerformed);
		sqrt.setOnClickListener(actionPerformed);
		square.setOnClickListener(actionPerformed);
		factorial.setOnClickListener(actionPerformed);
		bksp.setOnClickListener(actionPerformed);
		left.setOnClickListener(actionPerformed);
		right.setOnClickListener(actionPerformed);
		dot.setOnClickListener(actionPerformed);
		exit.setOnClickListener(actionPerformed);
		drg.setOnClickListener(actionPerformed);
		mc.setOnClickListener(actionPerformed);
		c.setOnClickListener(actionPerformed);
	}

	/**
	 * �������׽
	 */
	String[] TipCommand = new String[500];
	int tip_i = 0;// TipCommand��ָ��
	private OnClickListener actionPerformed = new OnClickListener() {

		public void onClick(View v) {
			// �����ϵ������ȡ
			String command = ((Button) v).getText().toString();
			// ��ʾ���ϵ��ַ���
			String str = input.getText().toString();
			// ��������Ƿ�Ϸ�
			if (equals_flag == false
					&& "0123456789.()sincostanlnlogn!+-���¡�^".indexOf(command) != -1) {
				// �����ʾ���ϵ��ַ����Ƿ�Ϸ�
				if (right(str)) {
					if ("+-���¡�^)".indexOf(command) != -1) {
						for (int i = 0; i < str.length(); i++) {
							TipCommand[tip_i] = String.valueOf(str.charAt(i));
							tip_i++;
						}
						vbegin = false;
					}
				} else {
					input.setText("0");
					vbegin = true;
					tip_i = 0;
					tip_lock = true;
					tip.setText("welcome use the APP��");
				}

				equals_flag = true;
			}
			if (tip_i > 0)
				TipChecker(TipCommand[tip_i - 1], command);
			else if (tip_i == 0) {
				TipChecker("#", command);
			}
			if ("0123456789.()sincostanlnlogn!+-���¡�^".indexOf(command) != -1
					&& tip_lock) {
				TipCommand[tip_i] = command;
				tip_i++;
			}
			// ��������ȷ����������Ϣ��ʾ����ʾ����
			if ("0123456789.()sincostanlnlogn!+-���¡�^".indexOf(command) != -1
					&& tip_lock) { // ��25������
				print(command);
				// ��������ˡ�DRG�������л���ǰ���ȽǶ��ƣ������л���Ľ����ʾ�������Ϸ���
			} else if (command.compareTo("DRG") == 0 && tip_lock) {
				if (drg_flag == true) {
					drg_flag = false;
					_drg.setText("   RAD");
				} else {
					drg_flag = true;
					_drg.setText("   DEG");
				}
				// �������ʱ�˸�����������ڰ�=֮ǰ
			} else if (command.compareTo("Bksp") == 0 && equals_flag) {
				// һ��ɾ��3���ַ�
				if (TTO(str) == 3) {
					if (str.length() > 3)
						input.setText(str.substring(0, str.length() - 3));
					else if (str.length() == 3) {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
						tip.setText("welcome use the APP��");
					}
					// ����ɾ��2���ַ�
				} else if (TTO(str) == 2) {
					if (str.length() > 2)
						input.setText(str.substring(0, str.length() - 2));
					else if (str.length() == 2) {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
						tip.setText("welcome use the APP��");
					}
					// ����ɾ��һ���ַ�
				} else if (TTO(str) == 1) {
					// ��֮ǰ������ַ����Ϸ���ɾ��һ���ַ�
					if (right(str)) {
						if (str.length() > 1)
							input.setText(str.substring(0, str.length() - 1));
						else if (str.length() == 1) {
							input.setText("0");
							vbegin = true;
							tip_i = 0;
							tip.setText("welcome use the APP��");
						}
						// ��֮ǰ������ַ������Ϸ���ɾ��ȫ���ַ�
					} else {
						input.setText("0");
						vbegin = true;
						tip_i = 0;
						tip.setText("welcome use the APP��");
					}
				}
				if (input.getText().toString().compareTo("-") == 0
						|| equals_flag == false) {
					input.setText("0");
					vbegin = true;
					tip_i = 0;
					tip.setText("welcome use the APP��");
				}
				tip_lock = true;
				if (tip_i > 0)
					tip_i--;
				// ������ڰ�=֮�������˸��
			} else if (command.compareTo("Bksp") == 0 && equals_flag == false) {
				// ����ʾ����������Ϊ0
				input.setText("0");
				vbegin = true;
				tip_i = 0;
				tip_lock = true;
				tip.setText("welcome use the APP��");
				// ���������������
			} else if (command.compareTo("C") == 0) {
				// ����ʾ����������Ϊ0
				input.setText("0");
				// ���������־��Ϊtrue
				vbegin = true;
				// ��������λ����0
				tip_i = 0;
				// �������Լ�������
				tip_lock = true;
				// ��������=֮ǰ
				equals_flag = true;
				tip.setText("welcome use the APP��");
				// ���������ǡ�MC�����򽫴洢��������0
			} else if (command.compareTo("MC") == 0) {
				mem.setText("0");
				// �������exit�����˳�����
			} else if (command.compareTo("exit") == 0) {
				new AlertDialog.Builder(MainActivity.this).setTitle("ϵͳ��ʾ")//���öԻ������  
				  
			     .setMessage("��ȷ�ϼ�¼��Ҫ���ݺ����˳�ϵͳ��")//������ʾ������  
			  
			     .setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {//���ȷ����ť  
			  
			          
			  
			         @Override  
			  
			         public void onClick(DialogInterface dialog, int which) {//ȷ����ť����Ӧ�¼�  
			  
			             // TODO Auto-generated method stub  
			  
			             finish(); 
			             System.exit(0);
			  
			         }  
			  
			     }).setNegativeButton("����",new DialogInterface.OnClickListener() {//��ӷ��ذ�ť  
			  
			          
			  
			         @Override  
			  
			         public void onClick(DialogInterface dialog, int which) {//��Ӧ�¼�  
			  
			             // TODO Auto-generated method stub  
			  
			             Log.i("alertdialog"," �뱣�����ݣ�");  
			  
			         }  
			  
			     }).show();//�ڰ�����Ӧ�¼�����ʾ�˶Ի���  
			  
				
				
			} else if (command.compareTo("=") == 0 && tip_lock && right(str)// ����������=�ţ���������Ϸ�
					&& equals_flag) {
				tip_i = 0;
				// ���������Լ�������
				tip_lock = false;
				// ��������=֮��
				equals_flag = false;
				// ����ԭ����ʽ����
				str_old = str;
				// �滻��ʽ�е�����������ڼ���
				str = str.replaceAll("sin", "s");
				str = str.replaceAll("cos", "c");
				str = str.replaceAll("tan", "t");
				str = str.replaceAll("log", "g");
				str = str.replaceAll("ln", "l");
				str = str.replaceAll("n!", "!");
				// ���������־����true
				vbegin = true;
				// ��-1xת����-
				str_new = str.replaceAll("-", "-1��");
				// ������ʽ���
				new calc().process(str_new);
			}
			// �������Լ�������
			tip_lock = true;
		}

	};

	/*
	 * ��⺯������str����ǰ���﷨��� ΪTip����ʾ��ʽ�ṩ���ݣ���TipShow()���ʹ�� ��� �ַ� �����Ը���ĺϷ��ַ� 1 ��
	 * ����|��|-|.|���� 2 �� ���|��|�� ^ 3 . ����|���|��|�� ^ 4 ���� .|����|���|��|�� ^ 5 ���
	 * ����|��|.|���� 6 �� ^ �� |. | ���� 7 ���� ����|��|.
	 * 
	 * С����ǰ�����ʡ�ԣ���ʾ0 ���ֵ�һλ����Ϊ0
	 */
	private void TipChecker(String tipcommand1, String tipcommand2) {

		// Tipcode1��ʾ�������ͣ�Tipcode2��ʾ���ʽ�������
		int Tipcode1 = 0, Tipcode2 = 0;
		// ��ʾ��������
		int tiptype1 = 0, tiptype2 = 0;
		// ������
		int bracket = 0;
		// ��+-x�¡�^��������Ϊ��һλ
		if (tipcommand1.compareTo("#") == 0
				&& (tipcommand2.compareTo("��") == 0
						|| tipcommand2.compareTo("��") == 0
						|| tipcommand2.compareTo("+") == 0
						|| tipcommand2.compareTo(")") == 0
						|| tipcommand2.compareTo("��") == 0 || tipcommand2
						.compareTo("^") == 0)) {
			Tipcode1 = -1;
		}
		// ����洢�ַ��������һλ������
		else if (tipcommand1.compareTo("#") != 0) {
			if (tipcommand1.compareTo("(") == 0) {
				tiptype1 = 1;
			} else if (tipcommand1.compareTo(")") == 0) {
				tiptype1 = 2;
			} else if (tipcommand1.compareTo(".") == 0) {
				tiptype1 = 3;
			} else if ("0123456789".indexOf(tipcommand1) != -1) {
				tiptype1 = 4;
			} else if ("+-����".indexOf(tipcommand1) != -1) {
				tiptype1 = 5;
			} else if ("��^".indexOf(tipcommand1) != -1) {
				tiptype1 = 6;
			} else if ("sincostanloglnn!".indexOf(tipcommand1) != -1) {
				tiptype1 = 7;
			}
			// ����������İ�������
			if (tipcommand2.compareTo("(") == 0) {
				tiptype2 = 1;
			} else if (tipcommand2.compareTo(")") == 0) {
				tiptype2 = 2;
			} else if (tipcommand2.compareTo(".") == 0) {
				tiptype2 = 3;
			} else if ("0123456789".indexOf(tipcommand2) != -1) {
				tiptype2 = 4;
			} else if ("+-����".indexOf(tipcommand2) != -1) {
				tiptype2 = 5;
			} else if ("��^".indexOf(tipcommand2) != -1) {
				tiptype2 = 6;
			} else if ("sincostanloglnn!".indexOf(tipcommand2) != -1) {
				tiptype2 = 7;
			}

			switch (tiptype1) {
			case 1:
				// �����ź���ֱ�ӽ�������,��+x�¡������š�-�����㣩,����"��^"
				if (tiptype2 == 2
						|| (tiptype2 == 5 && tipcommand2.compareTo("-") != 0)
						|| tiptype2 == 6)
					Tipcode1 = 1;
				break;
			case 2:
				// �����ź���������ţ����֣���+-x��sin^...��
				if (tiptype2 == 1 || tiptype2 == 3 || tiptype2 == 4
						|| tiptype2 == 7)
					Tipcode1 = 2;
				break;
			case 3:
				// ��.������������Ż��ߡ�sincos...��
				if (tiptype2 == 1 || tiptype2 == 7)
					Tipcode1 = 3;
				// ��������������.��
				if (tiptype2 == 3)
					Tipcode1 = 8;
				break;
			case 4:
				// ���ֺ���ֱ�ӽ������Ż��ߡ�sincos...��
				if (tiptype2 == 1 || tiptype2 == 7)
					Tipcode1 = 4;
				break;
			case 5:
				// ��+-x�¡�����ֱ�ӽ������ţ���+-x�¡�^��
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6)
					Tipcode1 = 5;
				break;
			case 6:
				// ����^������ֱ�ӽ������ţ���+-x�¡�^���Լ���sincos...��
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6
						|| tiptype2 == 7)
					Tipcode1 = 6;
				break;
			case 7:
				// ��sincos...������ֱ�ӽ������š�+-x�¡�^���Լ���sincos...��
				if (tiptype2 == 2 || tiptype2 == 5 || tiptype2 == 6
						|| tiptype2 == 7)
					Tipcode1 = 7;
				break;
			}
		}
		// ���С������ظ��ԣ�Tipconde1=0,��������ǰ��Ĺ���
		if (Tipcode1 == 0 && tipcommand2.compareTo(".") == 0) {
			int tip_point = 0;
			for (int i = 0; i < tip_i; i++) {
				// ��֮ǰ����һ��С����㣬��С���������1
				if (TipCommand[i].compareTo(".") == 0) {
					tip_point++;
				}
				// ���������¼��������֮һ��С�����������
				if (TipCommand[i].compareTo("sin") == 0
						|| TipCommand[i].compareTo("cos") == 0
						|| TipCommand[i].compareTo("tan") == 0
						|| TipCommand[i].compareTo("log") == 0
						|| TipCommand[i].compareTo("ln") == 0
						|| TipCommand[i].compareTo("n!") == 0
						|| TipCommand[i].compareTo("��") == 0
						|| TipCommand[i].compareTo("^") == 0
						|| TipCommand[i].compareTo("��") == 0
						|| TipCommand[i].compareTo("��") == 0
						|| TipCommand[i].compareTo("-") == 0
						|| TipCommand[i].compareTo("+") == 0
						|| TipCommand[i].compareTo("(") == 0
						|| TipCommand[i].compareTo(")") == 0) {
					tip_point = 0;
				}
			}
			tip_point++;
			// ��С�����������1������С�����ظ���
			if (tip_point > 1) {
				Tipcode1 = 8;
			}
		}
		// ����������Ƿ�ƥ��
		if (Tipcode1 == 0 && tipcommand2.compareTo(")") == 0) {
			int tip_right_bracket = 0;
			for (int i = 0; i < tip_i; i++) {
				// �������һ�������ţ��������1
				if (TipCommand[i].compareTo("(") == 0) {
					tip_right_bracket++;
				}
				// �������һ�������ţ��������1
				if (TipCommand[i].compareTo(")") == 0) {
					tip_right_bracket--;
				}
			}
			// ��������ż���=0,����û����Ӧ���������뵱ǰ������ƥ��
			if (tip_right_bracket == 0) {
				Tipcode1 = 10;
			}
		}
		// �������=�ĺϷ���
		if (Tipcode1 == 0 && tipcommand2.compareTo("=") == 0) {
			// ����ƥ����
			int tip_bracket = 0;
			for (int i = 0; i < tip_i; i++) {
				if (TipCommand[i].compareTo("(") == 0) {
					tip_bracket++;
				}
				if (TipCommand[i].compareTo(")") == 0) {
					tip_bracket--;
				}
			}
			// ������0�����������Ż���δƥ���
			if (tip_bracket > 0) {
				Tipcode1 = 9;
				bracket = tip_bracket;
			} else if (tip_bracket == 0) {
				// ��ǰһ���ַ�������֮һ������=�Ų��Ϸ�
				if ("��^sincostanloglnn!".indexOf(tipcommand1) != -1) {
					Tipcode1 = 6;
				}
				// ��ǰһ���ַ�������֮һ������=�Ų��Ϸ�
				if ("+-����".indexOf(tipcommand1) != -1) {
					Tipcode1 = 5;
				}
			}
		}
		// ������ʽ����֮һ������ʾ��Ӧ�İ�����Ϣ
		if (tipcommand2.compareTo("MC") == 0)
			Tipcode2 = 1;
		if (tipcommand2.compareTo("C") == 0)
			Tipcode2 = 2;
		if (tipcommand2.compareTo("DRG") == 0)
			Tipcode2 = 3;
		if (tipcommand2.compareTo("Bksp") == 0)
			Tipcode2 = 4;
		if (tipcommand2.compareTo("sin") == 0)
			Tipcode2 = 5;
		if (tipcommand2.compareTo("cos") == 0)
			Tipcode2 = 6;
		if (tipcommand2.compareTo("tan") == 0)
			Tipcode2 = 7;
		if (tipcommand2.compareTo("log") == 0)
			Tipcode2 = 8;
		if (tipcommand2.compareTo("ln") == 0)
			Tipcode2 = 9;
		if (tipcommand2.compareTo("n!") == 0)
			Tipcode2 = 10;
		if (tipcommand2.compareTo("��") == 0)
			Tipcode2 = 11;
		if (tipcommand2.compareTo("^") == 0)
			Tipcode2 = 12;
		// ��ʾ�����ʹ�����Ϣ
		TipShow(bracket, Tipcode1, Tipcode2, tipcommand1, tipcommand2);

	}

	/*
	 * ����Tip��Ϣ����ǿ�˻���������TipChecker()���ʹ��
	 */
	private void TipShow(int bracket, int tipcode1, int tipcode2,
			String tipcommand1, String tipcommand2) {

		String tipmessage = "";
		if (tipcode1 != 0)
			tip_lock = false;// ������������
		switch (tipcode1) {
		case -1:
			tipmessage = tipcommand2 + "  ������Ϊ��һ�����\n";
			break;
		case 1:
			tipmessage = tipcommand1 + "  ��Ӧ���룺����/(/./-/���� \n";
			break;
		case 2:
			tipmessage = tipcommand1 + "  ��Ӧ���룺)/��� \n";
			break;
		case 3:
			tipmessage = tipcommand1 + "  ��Ӧ���룺)/����/��� \n";
			break;
		case 4:
			tipmessage = tipcommand1 + "  ��Ӧ���룺)/./���� /��� \n";
			break;
		case 5:
			tipmessage = tipcommand1 + "  ��Ӧ���룺(/./����/���� \n";
			break;
		case 6:
			tipmessage = tipcommand1 + "  ��Ӧ���룺(/./���� \n";
			break;
		case 7:
			tipmessage = tipcommand1 + "  ��Ӧ���룺(/./���� \n";
			break;
		case 8:
			tipmessage = "С�����ظ�\n";
			break;
		case 9:
			tipmessage = "���ܼ��㣬ȱ�� " + bracket + " �� )";
			break;
		case 10:
			tipmessage = "����Ҫ  )";
			break;
		}
		switch (tipcode2) {
		case 1:
			tipmessage = tipmessage + "[MC �÷�: ������� MEM]";
			break;
		case 2:
			tipmessage = tipmessage + "[C �÷�: ����]";
			break;
		case 3:
			tipmessage = tipmessage + "[DRG �÷�: ѡ�� DEG �� RAD]";
			break;
		case 4:
			tipmessage = tipmessage + "[Bksp �÷�: �˸�]";
			break;
		case 5:
			tipmessage = tipmessage + "sin �����÷�ʾ����\n"
					+ "DEG��sin30 = 0.5      RAD��sin1 = 0.84\n"
					+ "ע������������һ��ʹ��ʱҪ�����ţ��磺\n" + "sin(cos45)��������sincos45";
			break;
		case 6:
			tipmessage = tipmessage + "cos �����÷�ʾ����\n"
					+ "DEG��cos60 = 0.5      RAD��cos1 = 0.54\n"
					+ "ע������������һ��ʹ��ʱҪ�����ţ��磺\n" + "cos(sin45)��������cossin45";
			break;
		case 7:
			tipmessage = tipmessage + "tan �����÷�ʾ����\n"
					+ "DEG��tan45 = 1      RAD��tan1 = 1.55\n"
					+ "ע������������һ��ʹ��ʱҪ�����ţ��磺\n" + "tan(cos45)��������tancos45";
			break;
		case 8:
			tipmessage = tipmessage + "log �����÷�ʾ����\n"
					+ "log10 = log(5+5) = 1\n" + "ע������������һ��ʹ��ʱҪ�����ţ��磺\n"
					+ "log(tan45)��������logtan45";
			break;
		case 9:
			tipmessage = tipmessage + "ln �����÷�ʾ����\n"
					+ "ln10 = le(5+5) = 2.3   lne = 1\n"
					+ "ע������������һ��ʹ��ʱҪ�����ţ��磺\n" + "ln(tan45)��������lntan45";
			break;
		case 10:
			tipmessage = tipmessage + "n! �����÷�ʾ����\n"
					+ "n!3 = n!(1+2) = 3��2��1 = 6\n" + "ע������������һ��ʹ��ʱҪ�����ţ��磺\n"
					+ "n!(log1000)��������n!log1000";
			break;
		case 11:
			tipmessage = tipmessage + "�� �÷�ʾ����������θ���\n"
					+ "�磺27��3�θ�Ϊ  27��3 = 3\n" + "ע������������һ��ʹ��ʱҪ�����ţ��磺\n"
					+ "(����)��(����) �� (n!3)��(log100) = 2.45";
			break;
		case 12:
			tipmessage = tipmessage + "^ �÷�ʾ�����������ƽ��\n" + "�磺2��3�η�Ϊ  2^3 = 8\n"
					+ "ע������������һ��ʹ��ʱҪ�����ţ��磺\n"
					+ "(����)��(����) �� (n!3)^(log100) = 36";
			break;
		}
		// ����ʾ��Ϣ��ʾ��tip
		tip.setText(tipmessage);

	}

	/**
	 * ����Ϣ��ʾ����ʾ����
	 */
	private void print(String str) {
		// ���������
		if (vbegin) {
			input.setText(str);
		} else {
			input.append(str);
		}
		vbegin = false;
	}

	/*
	 * ��⺯��������ֵΪ3��2��1 ��ʾӦ��һ��ɾ�������� Three+Two+One = TTO ΪBksp��ť��ɾ����ʽ�ṩ����
	 * ����3����ʾstrβ��Ϊsin��cos��tan��log�е�һ����Ӧ��һ��ɾ��3�� ����2����ʾstrβ��Ϊln��n!�е�һ����Ӧ��һ��ɾ��2��
	 * ����1����ʾΪ������3��2������������ֻ��ɾ��һ���������Ƿ��ַ�ʱҪ���⿼�ǣ�Ӧ������
	 */
	private int TTO(String str) {
		if ((str.charAt(str.length() - 1) == 'n'
				&& str.charAt(str.length() - 2) == 'i' && str.charAt(str
				.length() - 3) == 's')
				|| (str.charAt(str.length() - 1) == 's'
						&& str.charAt(str.length() - 2) == 'o' && str
						.charAt(str.length() - 3) == 'c')
				|| (str.charAt(str.length() - 1) == 'n'
						&& str.charAt(str.length() - 2) == 'a' && str
						.charAt(str.length() - 3) == 't')
				|| (str.charAt(str.length() - 1) == 'g'
						&& str.charAt(str.length() - 2) == 'o' && str
						.charAt(str.length() - 3) == 'l')) {
			return 3;
		} else if ((str.charAt(str.length() - 1) == 'n' && str.charAt(str
				.length() - 2) == 'l')
				|| (str.charAt(str.length() - 1) == '!' && str.charAt(str
						.length() - 2) == 'n')) {
			return 2;
		} else {
			return 1;
		}
	}

	/*
	 * �ж�һ��str�Ƿ��ǺϷ��ģ�����ֵΪtrue��false
	 * ֻ����0123456789.()sincostanlnlogn!+-���¡�^���ǺϷ���str������true
	 * �����˳�0123456789.()sincostanlnlogn!+-���¡�^������ַ���strΪ�Ƿ��ģ�����false
	 */
	private boolean right(String str) {
		int i = 0;
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) != '0' && str.charAt(i) != '1'
					&& str.charAt(i) != '2' && str.charAt(i) != '3'
					&& str.charAt(i) != '4' && str.charAt(i) != '5'
					&& str.charAt(i) != '6' && str.charAt(i) != '7'
					&& str.charAt(i) != '8' && str.charAt(i) != '9'
					&& str.charAt(i) != '.' && str.charAt(i) != '-'
					&& str.charAt(i) != '+' && str.charAt(i) != '��'
					&& str.charAt(i) != '��' && str.charAt(i) != '��'
					&& str.charAt(i) != '^' && str.charAt(i) != 's'
					&& str.charAt(i) != 'i' && str.charAt(i) != 'n'
					&& str.charAt(i) != 'c' && str.charAt(i) != 'o'
					&& str.charAt(i) != 't' && str.charAt(i) != 'a'
					&& str.charAt(i) != 'l' && str.charAt(i) != 'g'
					&& str.charAt(i) != '(' && str.charAt(i) != ')'
					&& str.charAt(i) != '!')
				break;
		}
		if (i == str.length()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * ����������ģ�
	 * ֻҪ�����ʽ�������ַ�������calc().process()�Ϳ���ʵ�м����� �㷨�������¼����֣�
	 *  1�����㲿��
	 * process(String str) ��Ȼ�����ǽ����ڲ���޴��������� 
	 * 2�����ݸ�ʽ�� FP(double n) ʹ�������൱�ľ�ȷ��
	 * 3���׳��㷨 N(double n) ����n!�����������
	 *  4��������ʾ showError(int code ,String str)
	 * �����󷵻�
	 */
	public class calc {

		public calc() {

		}

		final int MAXLEN = 500;

		/*
		 * ������ʽ ��������ɨ�裬������numberջ���������operatorջ 
		 * +-�������ȼ�Ϊ1��
		 * ���»������ȼ�Ϊ2��
		 * log ln sin cos tan n!�������ȼ�Ϊ3��
		 * ��^�������ȼ�Ϊ4 
		 * �����ڲ�����������ͬ����������ȼ���4
		 * ��ǰ��������ȼ�����ջ��ѹջ��
		 * ����ջ������һ�����������������������
		 *  �ظ�ֱ����ǰ���������ջ��
		 *   ɨ������ʣ�µ���������������μ���
		 */
		public void process(String str) {
			int weightPlus = 0, topOp = 0, topNum = 0, flag = 1, weightTemp = 0;
			// weightPlusΪͬһ�����µĻ������ȼ���weightTemp��ʱ��¼���ȼ��ı仯
			// topOpΪweight[]��operator[]�ļ�������topNumΪnumber[]�ļ�����
			// flagΪ�������ļ�������1Ϊ������-1Ϊ����
			int weight[]; // ����operatorջ������������ȼ�����topOp����
			double number[]; // �������֣���topNum����
			char ch, ch_gai, operator[];// operator[]�������������topOp����
			String num;// ��¼���֣�str��+-����()sctgl!��^�ֶΣ�+-����()sctgl!��^�ַ�֮����ַ�����Ϊ����
			weight = new int[MAXLEN];
			number = new double[MAXLEN];
			operator = new char[MAXLEN];
			String expression = str;
			StringTokenizer expToken = new StringTokenizer(expression,
					"+-����()sctgl!��^");
			int i = 0;
			while (i < expression.length()) {
				ch = expression.charAt(i);
				// �ж�������
				if (i == 0) {
					if (ch == '-')
						flag = -1;
				} else if (expression.charAt(i - 1) == '(' && ch == '-')
					flag = -1;
				// ȡ�����֣�������������ת�Ƹ�����
				if (ch <= '9' && ch >= '0' || ch == '.' || ch == 'E') {
					num = expToken.nextToken();
					ch_gai = ch;
					Log.e("guojs", ch + "--->" + i);
					// ȡ����������
					while (i < expression.length()
							&& (ch_gai <= '9' && ch_gai >= '0' || ch_gai == '.' || ch_gai == 'E')) {
						ch_gai = expression.charAt(i++);
						Log.e("guojs", "i��ֵΪ��" + i);
					}
					// ��ָ���˻�֮ǰ��λ��
					if (i >= expression.length())
						i -= 1;
					else {
						i -= 2;
					}
					if (num.compareTo(".") == 0)
						number[topNum++] = 0;
					// ����������ת�Ƹ�����
					else {
						number[topNum++] = Double.parseDouble(num) * flag;
						flag = 1;
					}
				}
				// ��������������ȼ�
				if (ch == '(')
					weightPlus += 4;
				if (ch == ')')
					weightPlus -= 4;
				if (ch == '-' && flag == 1 || ch == '+' || ch == '��'
						|| ch == '��' || ch == 's' || ch == 'c' || ch == 't'
						|| ch == 'g' || ch == 'l' || ch == '!' || ch == '��'
						|| ch == '^') {
					switch (ch) {
					// +-�����ȼ���ͣ�Ϊ1
					case '+':
					case '-':
						weightTemp = 1 + weightPlus;
						break;
					// x�µ����ȼ��Ըߣ�Ϊ2
					case '��':
					case '��':
						weightTemp = 2 + weightPlus;
						break;
					// sincos֮�����ȼ�Ϊ3
					case 's':
					case 'c':
					case 't':
					case 'g':
					case 'l':
					case '!':
						weightTemp = 3 + weightPlus;
						break;
					// �������ȼ�Ϊ4
					// case '^':
					// case '��':
					default:
						weightTemp = 4 + weightPlus;
						break;
					}
					// �����ǰ���ȼ����ڶ�ջ����Ԫ�أ���ֱ����ջ
					if (topOp == 0 || weight[topOp - 1] < weightTemp) {
						weight[topOp] = weightTemp;
						operator[topOp] = ch;
						topOp++;
						// ���򽫶�ջ����������ȡ����ֱ����ǰ��ջ��������������ȼ�С�ڵ�ǰ�����
					} else {
						while (topOp > 0 && weight[topOp - 1] >= weightTemp) {
							switch (operator[topOp - 1]) {
							// ȡ�������������ӦԪ�ؽ�������
							case '+':
								number[topNum - 2] += number[topNum - 1];
								break;
							case '-':
								number[topNum - 2] -= number[topNum - 1];
								break;
							case '��':
								number[topNum - 2] *= number[topNum - 1];
								break;
							// �жϳ���Ϊ0�����
							case '��':
								if (number[topNum - 1] == 0) {
									showError(1, str_old);
									return;
								}
								number[topNum - 2] /= number[topNum - 1];
								break;
							case '��':
								if (number[topNum - 1] == 0
										|| (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
									showError(2, str_old);
									return;
								}
								number[topNum - 2] = Math.pow(
										number[topNum - 2],
										1 / number[topNum - 1]);
								break;
							case '^':
								number[topNum - 2] = Math.pow(
										number[topNum - 2], number[topNum - 1]);
								break;
							// ����ʱ���нǶȻ��ȵ��жϼ�ת��
							// sin
							case 's':
								if (drg_flag == true) {
									number[topNum - 1] = Math
											.sin((number[topNum - 1] / 180)
													* pi);
								} else {
									number[topNum - 1] = Math
											.sin(number[topNum - 1]);
								}
								topNum++;
								break;
							// cos
							case 'c':
								if (drg_flag == true) {
									number[topNum - 1] = Math
											.cos((number[topNum - 1] / 180)
													* pi);
								} else {
									number[topNum - 1] = Math
											.cos(number[topNum - 1]);
								}
								topNum++;
								break;
							// tan
							case 't':
								if (drg_flag == true) {
									if ((Math.abs(number[topNum - 1]) / 90) % 2 == 1) {
										showError(2, str_old);
										return;
									}
									number[topNum - 1] = Math
											.tan((number[topNum - 1] / 180)
													* pi);
								} else {
									if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
										showError(2, str_old);
										return;
									}
									number[topNum - 1] = Math
											.tan(number[topNum - 1]);
								}
								topNum++;
								break;
							// log
							case 'g':
								if (number[topNum - 1] <= 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = Math
										.log10(number[topNum - 1]);
								topNum++;
								break;
							// ln
							case 'l':
								if (number[topNum - 1] <= 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = Math
										.log(number[topNum - 1]);
								topNum++;
								break;
							// �׳�
							case '!':
								if (number[topNum - 1] > 170) {
									showError(3, str_old);
									return;
								} else if (number[topNum - 1] < 0) {
									showError(2, str_old);
									return;
								}
								number[topNum - 1] = N(number[topNum - 1]);
								topNum++;
								break;
							}
							// ����ȡ��ջ����һ��Ԫ�ؽ����ж�
							topNum--;
							topOp--;
						}
						// ����������ջ
						weight[topOp] = weightTemp;
						operator[topOp] = ch;
						topOp++;
					}
				}
				i++;
			}
			// ����ȡ����ջ���������������
			while (topOp > 0) {
				// +-xֱ�ӽ�����ĺ���λ��ȡ������
				switch (operator[topOp - 1]) {
				case '+':
					number[topNum - 2] += number[topNum - 1];
					break;
				case '-':
					number[topNum - 2] -= number[topNum - 1];
					break;
				case '��':
					number[topNum - 2] *= number[topNum - 1];
					break;
				// �漰������ʱҪ���ǳ�������Ϊ������
				case '��':
					if (number[topNum - 1] == 0) {
						showError(1, str_old);
						return;
					}
					number[topNum - 2] /= number[topNum - 1];
					break;
				case '��':
					if (number[topNum - 1] == 0
							|| (number[topNum - 2] < 0 && number[topNum - 1] % 2 == 0)) {
						showError(2, str_old);
						return;
					}
					number[topNum - 2] = Math.pow(number[topNum - 2],
							1 / number[topNum - 1]);
					break;
				case '^':
					number[topNum - 2] = Math.pow(number[topNum - 2],
							number[topNum - 1]);
					break;
				// sin
				case 's':
					if (drg_flag == true) {
						number[topNum - 1] = Math
								.sin((number[topNum - 1] / 180) * pi);
					} else {
						number[topNum - 1] = Math.sin(number[topNum - 1]);
					}
					topNum++;
					break;
				// cos
				case 'c':
					if (drg_flag == true) {
						number[topNum - 1] = Math
								.cos((number[topNum - 1] / 180) * pi);
					} else {
						number[topNum - 1] = Math.cos(number[topNum - 1]);
					}
					topNum++;
					break;
				// tan
				case 't':
					if (drg_flag == true) {
						if ((Math.abs(number[topNum - 1]) / 90) % 2 == 1) {
							showError(2, str_old);
							return;
						}
						number[topNum - 1] = Math
								.tan((number[topNum - 1] / 180) * pi);
					} else {
						if ((Math.abs(number[topNum - 1]) / (pi / 2)) % 2 == 1) {
							showError(2, str_old);
							return;
						}
						number[topNum - 1] = Math.tan(number[topNum - 1]);
					}
					topNum++;
					break;
				// ����log
				case 'g':
					if (number[topNum - 1] <= 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = Math.log10(number[topNum - 1]);
					topNum++;
					break;
				// ��Ȼ����ln
				case 'l':
					if (number[topNum - 1] <= 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = Math.log(number[topNum - 1]);
					topNum++;
					break;
				// �׳�
				case '!':
					if (number[topNum - 1] > 170) {
						showError(3, str_old);
						return;
					} else if (number[topNum - 1] < 0) {
						showError(2, str_old);
						return;
					}
					number[topNum - 1] = N(number[topNum - 1]);
					topNum++;
					break;
				}
				// ȡ��ջ��һ��Ԫ�ؼ���
				topNum--;
				topOp--;
			}
			// ���������̫����ʾ������Ϣ
			if (number[0] > 7.3E306) {
				showError(3, str_old);
				return;
			}
			// ������ս��
			input.setText(String.valueOf(FP(number[0])));
			tip.setText("������ϣ�Ҫ�����밴����� C");
			mem.setText(str_old + "=" + String.valueOf(FP(number[0])));
		}

		/*
		 * FP = floating point ����С��λ�����ﵽ���� ��������
		 * 0.6-0.2=0.39999999999999997���������FP���ɽ����ʹ����Ϊ0.4 ����ʽ����Ϊ15λ
		 */
		public double FP(double n) {
			// NumberFormat format=NumberFormat.getInstance(); //����һ����ʽ����f
			// format.setMaximumFractionDigits(18); //����С��λ�ĸ�ʽ
			DecimalFormat format = new DecimalFormat("0.#############");
			return Double.parseDouble(format.format(n));
		}

		/*
		 * �׳��㷨
		 */
		public double N(double n) {
			int i = 0;
			double sum = 1;
			// ���ν�С�ڵ���n��ֵ���
			for (i = 1; i <= n; i++) {
				sum = sum * i;
			}
			return sum;
		}

		/*
		 * ������ʾ������"="֮��������ʽ��process()�����У����ִ����������ʾ
		 */
		public void showError(int code, String str) {
			String message = "";
			switch (code) {
			case 1:
				message = "�㲻��������";
				break;
			case 2:
				message = "������ʽ����";
				break;
			case 3:
				message = "ֵ̫���ˣ�������Χ";
			}
			input.setText("\"" + str + "\"" + ": " + message);
			tip.setText(message + "\n" + "������ϣ�Ҫ�����밴����� C");
		}
	}

}

