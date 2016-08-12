/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Bundle;
import ca.uhn.fhir.model.api.BundleEntry;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import ca.uhn.fhir.parser.*;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.json.stream.JsonParsingException;
/**
 *
 * @author Administrator
 */
public class TestFrame extends javax.swing.JFrame {
    
    public enum ObjectEnum {
        Name(0), KTAS(1), Consiousness(2), Vital(3), SpO2(4), Hb(5), K(6), Lactate(7), Transportation(8), Disease(9);
        private int value;
        private ObjectEnum(int value){
            this.value = value;
        }
        public int getValue(){
            return this.value;
        }
        public ObjectEnum valueOf(int value){
            switch(value){
                case 0:
                    return this.Name;
                case 1:
                    return this.KTAS;
                case 2:
                    return this.Consiousness;
                case 3:
                    return this.Vital;
                case 4:
                    return this.SpO2;
                case 5:
                    return this.Hb;
                case 6:
                    return this.K;
                case 7:
                    return this.Lactate;
                case 8:
                    return this.Transportation;
                case 9:
                    return this.Disease;
            }
            return this;
        }
    }  
    
    private int testframe = 0;
    
    /**
     * Creates new form TestFrame
     */
//    private javax.swing.JTextField jTextField2 = new javax.swing.JTextField();
    //메인 클래스 작동 시 TestFrame 생성자 작동
    public TestFrame() {
        //jframe undecorated:배경화면x
        //new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height)
        //layout size default, auto resize X, preferred size custom code=>
        //new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height)
        getContentPane().setBackground(Color.BLACK);
        initComponents();
//        jTable1.getTableHeader().setSize(jTable1.getSize().width, jScrollPane1.getSize().height/11);
        jTable1.setRowHeight(jScrollPane1.getSize().height/11);
        System.out.println(jScrollPane1.getSize().height/11);
        List<NedisData> nd = patientInfo();
        screenMake(nd);
    }
    
    //환자 정보 및 해당 환자의 응급 정보를 가져와서 리턴
    public List<NedisData> patientInfo(){
        HttpClient htc = new DefaultHttpClient();
        List<NedisData> nd = new ArrayList<NedisData>();
        try{
            HttpGet htg = new HttpGet("http://52.79.148.103:8080/SFHIRServer2/fhir/Patient?active=true");
            HttpResponse hr = htc.execute(htg);
            HttpEntity he = hr.getEntity();
//            System.out.println("entered");
            if(he != null){
                BufferedReader rd = new BufferedReader(new InputStreamReader(he.getContent()));
		String line = "";
                String totline = "";
		while ((line = rd.readLine()) != null) {
                    totline += line;
		}
                FhirContext ctx = FhirContext.forDstu2();
                JsonParser fjp = (JsonParser) ctx.newJsonParser();
                Bundle cont = fjp.parseBundle(totline);
                List<BundleEntry> conten = cont.getEntries();
                System.out.println(conten.size());
                //patient id
                for(int i = 0 ; i < conten.size() ; i++){
                    nd.add(nedisInfo(i));
                }
                testframe++;
                return nd;
//                System.out.println(totline);
            } else{
                System.out.println("nullcontent");
            }
        } catch(JsonParsingException e){
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
        } finally {
            htc.getConnectionManager().shutdown();
	}
        return null;
    }
    
    //해당 환자의 응급 정보 리턴
    public NedisData nedisInfo(int i){
        HttpClient htc = new DefaultHttpClient();
        NedisData nd = new NedisData();
        try{
            HttpGet htg = new HttpGet();
            if(testframe%2==0 || testframe==0){
                htg = new HttpGet("http://52.79.148.103:8080/SFHIRServer2/fhir/Patient/"+(i+1)+"/$everything");
            } else if(testframe%2==1){
                htg = new HttpGet("http://52.79.148.103:8080/SFHIRServer2/fhir/Patient/"+(i+11)+"/$everything");
            }
            HttpResponse hr = htc.execute(htg);
            HttpEntity he = hr.getEntity();
//            System.out.println("entered");
            if(he != null){
                BufferedReader rd = new BufferedReader(new InputStreamReader(he.getContent()));
		String line = "";
                String totline = "";
		while ((line = rd.readLine()) != null) {
                    totline += line;
		}
                FhirContext ctx = FhirContext.forDstu2();
                JsonParser fjp = (JsonParser) ctx.newJsonParser();
                Bundle cont = fjp.parseBundle(totline);
                List<BundleEntry> conten = cont.getEntries();
//                System.out.println("this:"+conten.size());
                for(int index = 0 ; index < conten.size() ; index++){
                    IResource ir = conten.get(index).getResource();
                    String rn = ir.getResourceName();
                    if(rn.equals("Patient")){
                        Patient p = (Patient) ir;
                        nd.setName(p.getNameFirstRep().getFamilyFirstRep().getValueAsString());
                        QuantityDt qd = (QuantityDt) p.getUndeclaredExtensions().get(0).getValue();
                        nd.setAge(qd.getValue().intValue());
                    } else if(rn.equals("Encounter")){
                        Encounter e = (Encounter) ir;
                        CodeableConceptDt ccd = (CodeableConceptDt)e.getHospitalization().getUndeclaredExtensions().get(0).getValue();
                        nd.setPriority(myPriorityCodesEnum.valueOf(e.getPriority().getCodingFirstRep().getCode()));
                        nd.setTransportation(TransportationEnum.valueOf(ccd.getCodingFirstRep().getCode()));
                    } else if(rn.equals("Observation")){
                        Observation o = (Observation) ir;
                        String entcode = o.getCode().getCodingFirstRep().getCode();
                        if(entcode.equals("dys")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setDyslitolic(qd.getValue().intValue());
                        } else if(entcode.equals("sys")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setSyslitolic(qd.getValue().intValue());
                        } else if(entcode.equals("hb")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setHb(qd.getValue().intValue());
                        } else if(entcode.equals("k")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setK(qd.getValue().intValue());
                        } else if(entcode.equals("vital")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setVital(qd.getValue().intValue());
                        } else if(entcode.equals("vital2")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setVital2(qd.getValue().intValue());
                        } else if(entcode.equals("oxygen")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setOxygen(qd.getValue().intValue());
                        } else if(entcode.equals("lactate")){
                            QuantityDt qd = (QuantityDt) o.getValue();
                            nd.setLactate(qd.getValue().intValue());
                        } 
                    } else if(rn.equals("Condition")){
                        Condition c = (Condition) ir;
                        nd.setConsiousness(ConsiousnessEnum.valueOf(c.getCode().getCodingFirstRep().getCode()));
                        nd.setSeverity(SeverityEnum.valueOf(c.getSeverity().getCodingFirstRep().getCode()));
                    }
                    
                }
                return nd;
//                System.out.println(totline);
            } else{
                System.out.println("nullcontent");
                return null;
            }
        } catch(JsonParsingException e){
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
	} catch (IOException e) {
            e.printStackTrace();
        } finally {
            htc.getConnectionManager().shutdown();
	}
        return null;
    }
    
    //FHIR 서버와의 트랜잭션으로 받아온 NedisData 값을 화면에 업데이트
    public void screenMake(List<NedisData> nd){
//        ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testpic.jpg");
        this.jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
                setBorder(new MatteBorder(1, 1, 1, 1, Color.WHITE));             
                setHorizontalAlignment( JLabel.CENTER );
                setIcon(null);
                if ((row%2)==0) {
                    setBackground(Color.GRAY);
                    if(row == 0){
                        if(column>0 && column<2 || column>7){
                            setBackground(Color.BLUE);
                        } else if(column>1 && column<5){
                            setBackground(Color.GREEN);
                        } else if(column>4 && column<8){
                            setBackground(Color.ORANGE);
                        }
                    }
                } else {
                    setBackground(Color.BLACK);
                }
//                if(column==1&&row!=0){
//                    setIcon(ig);
//                }                
                if(row!=0){
                    ObjectEnum oe = ObjectEnum.Name;
                    oe = oe.valueOf(column);
                    switch(oe){
                        //define enum
                        case Name:
                            setText(nd.get(row-1).getName()+"** ("+nd.get(row-1).getAge()+")");
                            break;
                        case KTAS:
                            String setc = nd.get(row-1).getSeverity().getCode();
                            if(setc.equals("L1")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testpic1.png");
                                setIcon(ig);
                            } else if(setc.equals("L2")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testpic2.png");
                                setIcon(ig);
                            } else if(setc.equals("L3")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testpic3.png");
                                setIcon(ig);
                            } else if(setc.equals("L4")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testpic4.png");
                                setIcon(ig);
                            } else if(setc.equals("L5")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testpic5.png");
                                setIcon(ig);
                            }
                            break;
                        case Consiousness:
                            String setc2 = nd.get(row-1).getConsiousness().getCode();
                            if(setc2.equals("A")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testbg.png");
                                setIcon(ig);
                            } else if(setc2.equals("V")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testbg.png");
                                setIcon(ig);
                            } else if(setc2.equals("P")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testbg.png");
                                setIcon(ig);
                            } else if(setc2.equals("U")){
                                ImageIcon ig = new ImageIcon("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\JavaApplication1\\testbg.png");
                                setIcon(ig);
                            }
//                            setText(nd.get(row-1).getConsiousness().getCode());
                            break;        
                        case Vital:
                            setText(nd.get(row-1).getDyslitolic()+"/"+nd.get(row-1).getSyslitolic()+"|"+nd.get(row-1).getVital());
                            break;    
                        case SpO2:
                            setText(nd.get(row-1).getVital2()+"/"+nd.get(row-1).getOxygen());
                            break;    
                        case Hb:
                            setText(nd.get(row-1).getHb()+"");
                            break;    
                        case K:
                            setText(nd.get(row-1).getK()+"");
                            break;    
                        case Lactate:
                            setText(nd.get(row-1).getLactate()+"");
                            break;
                        case Transportation:
                            setText(nd.get(row-1).getTransportation().getCode());
                            break;    
                        case Disease:
                            //?????
                            setText(nd.get(row-1).getPriority().getCode());
                            break;    
                        default:
                            break;        
                    }
                }
//                Border b = BorderFactory.createCompoundBorder();
//                b = BorderFactory.createCompoundBorder(b, noFocusBorder)
                table.repaint();
                return this;
            }
        });
    }
    
//    public Color getTableCellBackground(JTable table, int row, int col) {
//        TableCellRenderer renderer = table.getCellRenderer(row, col);
//        Component component = table.prepareRenderer(renderer, row, col);
////        renderer.getTableCellRendererComponent(table, new Object(), true, true, 0, 0);
//        component.setBackground(Color.red);
//        TableCellRenderer renderer2 = table.getCellRenderer(1, 1);
//        Component component2 = table.prepareRenderer(renderer2, 1, 1);
//        component2.setBackground(Color.blue);
//        return component.getBackground();
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setUndecorated(true);
        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(new java.awt.Font("SansSerif", 0, 48)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 153));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("환자개별상황");
        jTextField1.setPreferredSize(new Dimension(400*Toolkit.getDefaultToolkit().getScreenSize().width/400, 30*Toolkit.getDefaultToolkit().getScreenSize().height/300));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);
        jScrollPane1.setPreferredSize(new Dimension(400*Toolkit.getDefaultToolkit().getScreenSize().width/400, 260*Toolkit.getDefaultToolkit().getScreenSize().height/300));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jTable1.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"환자", "KTAS", "의식", "혈압 | 맥박", "호흡수 / SpO2", "Hb", "K", "Lactate", "내원수단", "외상유무"},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {"", "", "", "", "", "", "", "", "", null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "환자", "KTAS", "내원수단", "외상유무", "의식", "혈압 | 맥박", "호흡수 / SpO2", "Hb", "K", "Lactate"
            }
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setAutoscrolls(false);
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setPreferredSize(new Dimension(400*Toolkit.getDefaultToolkit().getScreenSize().width/400, 260*Toolkit.getDefaultToolkit().getScreenSize().height/300));
        jTable1.setRowHeight(16);
        jTable1.setTableHeader(null);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    //처음 시작 시 화면 생성 및 00초 주기로 화면 업데이트
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        TestFrame tf = new TestFrame();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                System.out.println("start");
                tf.setVisible(true);        
            }
        });
        Timer jobScheduler = new Timer();
        jobScheduler.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<NedisData> nd = tf.patientInfo();
                tf.screenMake(nd);
                tf.pack();
                System.out.println("transaction starts");
            }
        }, 1000, 5000);
        
        try {
           Thread.sleep(20000);
        } catch(InterruptedException ex) {
         //
        }
        jobScheduler.cancel();        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
