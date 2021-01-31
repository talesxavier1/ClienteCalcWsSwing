package org.ws.calc.cliente.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

//import org.apache.axis2.AxisFault;
//import org.apache.axis2.databinding.types.soapencoding.Decimal;
import org.ws.calc.cliente.stub.CalculadoraStub;


public class CalcView extends JFrame
{
	private JLabel lblValorResultado;
	private JLabel lblValorOperacao;
	DecimalFormat format = new DecimalFormat("###,##0.00");
	
	public CalcView(){
		this.setSize(320,190);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(CalcView.EXIT_ON_CLOSE);
		this.setLayout(null);
		
		
		JLabel lblValor1 = new JLabel("Valor 1:");
		lblValor1.setSize(45,15);
		lblValor1.setLocation(10,10);
		this.add(lblValor1);
		
		JTextField txtValor1 = new JTextField();
		txtValor1.setLocation(55,10);
		txtValor1.setSize(80,20);
		add(txtValor1);
		
		JLabel lblValor2 = new JLabel("Valor 2:");
		lblValor2.setSize(45,15);
		lblValor2.setLocation(10,35);
		this.add(lblValor2);
		
		JTextField txtValor2 = new JTextField();
		txtValor2.setLocation(55,35);
		txtValor2.setSize(80,20);
		add(txtValor2);
		
		JLabel lblOperacao = new JLabel("Operacao:");
		lblOperacao.setSize(60,15);
		lblOperacao.setLocation(10,60);
		this.add(lblOperacao);
		
		lblValorOperacao = new JLabel("#######");
		lblValorOperacao.setSize(60,15);
		lblValorOperacao.setLocation(75,60);
		this.add(lblValorOperacao);
		
		JLabel lblResultado = new JLabel("Resultado:");
		lblResultado.setSize(65,15);
		lblResultado.setLocation(10,85);
		this.add(lblResultado);
		
		lblValorResultado = new JLabel("#######");
		lblValorResultado.setSize(60,15);
		lblValorResultado.setLocation(75,85);
		this.add(lblValorResultado);
		
		JButton btnSoma = new JButton("Soma");
		btnSoma.setSize(120,25);
		btnSoma.setLocation(170,10);
		add(btnSoma);
		btnSoma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				somar(Double.parseDouble(txtValor1.getText()),Double.parseDouble( txtValor2.getText()));
				
			}});
		
		JButton btnSubtracao = new JButton("Subtração");
		btnSubtracao.setSize(120,25);
		btnSubtracao.setLocation(170,35);
		add(btnSubtracao);
		btnSubtracao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				subtracao(Double.parseDouble(txtValor1.getText()),Double.parseDouble( txtValor2.getText()));
			}});
		
		JButton btnMultiplicacao = new JButton("Multiplicação");
		btnMultiplicacao.setSize(120,25);
		btnMultiplicacao.setLocation(170,60);
		add(btnMultiplicacao);
		btnMultiplicacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Multiplicacao(Double.parseDouble(txtValor1.getText()),Double.parseDouble( txtValor2.getText()));
			}});
		
		JButton btnDivisao = new JButton("Divisão");
		btnDivisao.setSize(120,25);
		btnDivisao.setLocation(170,85);
		add(btnDivisao);
		btnDivisao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Divisao(Double.parseDouble(txtValor1.getText()),Double.parseDouble( txtValor2.getText()));
			}});
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setSize(120,25);
		btnLimpar.setLocation(170,110);
		add(btnLimpar);
		btnLimpar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				txtValor1.setText("");
				txtValor2.setText("");
				lblValorOperacao.setText("#######");
				lblValorResultado.setText("#######");
				
			}});
	}
	
	public void somar(double i, double j)
	{
		try 
		{
			CalculadoraStub stub = new CalculadoraStub("http://localhost:8080/axis2/services/Calculadora");
			CalculadoraStub.Soma req = new CalculadoraStub.Soma();
			req.setI(i);
			req.setJ(j);
			CalculadoraStub.SomaResponse response = stub.soma(req);
			lblValorOperacao.setText("SOMA");
			lblValorResultado.setText(format.format(response.get_return()));
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	public void subtracao(double i, double j)
	{
		try 
		{
			CalculadoraStub stub = new CalculadoraStub("http://localhost:8080/axis2/services/Calculadora");
			CalculadoraStub.Subtracao req = new CalculadoraStub.Subtracao();
			req.setI(i);
			req.setJ(j);
			CalculadoraStub.SubtracaoResponse response = stub.subtracao(req);
			lblValorOperacao.setText("SUBTRACAO");
			lblValorResultado.setText(format.format(response.get_return()));
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Divisao(double i, double j)
	{
		try 
		{
			CalculadoraStub stub = new CalculadoraStub("http://localhost:8080/axis2/services/Calculadora");
			CalculadoraStub.Divisao req = new CalculadoraStub.Divisao();
			req.setI(i);
			req.setJ(j);
			CalculadoraStub.DivisaoResponse response = stub.divisao(req);
			lblValorOperacao.setText("DIVISAO");
			lblValorResultado.setText(format.format(response.get_return()));
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Multiplicacao(double i, double j)
	{
		try 
		{
			CalculadoraStub stub = new CalculadoraStub("http://localhost:8080/axis2/services/Calculadora");
			CalculadoraStub.Multiplicacao req = new CalculadoraStub.Multiplicacao();
			req.setI(i);
			req.setJ(j);
			CalculadoraStub.MultiplicacaoResponse response = stub.multiplicacao(req);
			lblValorOperacao.setText("MULTIPLICACAO");
			lblValorResultado.setText(format.format(response.get_return()));
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new CalcView().setVisible(true);
	}
	
}
