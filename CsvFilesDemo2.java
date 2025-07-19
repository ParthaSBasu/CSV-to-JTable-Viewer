import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class CsvFilesDemo2 {

    static ArrayList<ArrayList<String>> gettingList(String filename){
        ArrayList<ArrayList<String>> myList=new ArrayList<>();
        File file=new File(filename);
        if(!file.exists()){
            System.out.println(filename+" does not exist!");
            return null;
        }
        try{
            BufferedReader reader=new BufferedReader(new FileReader(filename));
            String str;
            while((str=reader.readLine())!=null){
                String[] data=str.trim().split(",");
                ArrayList<String> row=new ArrayList<>();
                for(String s:data ) row.add(s);
                myList.add(row);
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return myList;
    }

    static HashMap<String,ArrayList<String>> gettingMap(ArrayList<ArrayList<String>> myList){
        HashMap<String,ArrayList<String>> myMap=new HashMap<>();
        ArrayList<String> headers=new ArrayList<>();
        for(String str:myList.get(0)) headers.add(str);
        int col=0;
        for(String heading:headers){
            ArrayList<String> column=new ArrayList<>();
            for(int row=1;row<myList.size();row++){
            column.add(myList.get(row).get(col));
            }
            myMap.put(heading,column);
            col++;
        }
        headers.clear();
        return myMap;
    }

    static void showTable(ArrayList<ArrayList<String>> myList){

        String[] columnData=new String[myList.get(0).size()];
        for(int index=0;index<myList.get(0).size();index++) {
            columnData[index]=myList.get(0).get(index);
        }
        String[][] rowData=new String[myList.size()-1][columnData.length];
        for(int i=1;i<myList.size();i++){
            for(int j=0;j<columnData.length;j++){
                rowData[i-1][j]=myList.get(i).get(j);
            }
        }
        
        JFrame frame=new JFrame();
        frame.setTitle("JTable Demonstration");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(700,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(20,20,20,20));
        panel.setPreferredSize(new Dimension(650,250));

        DefaultTableModel tableModel=new DefaultTableModel(rowData,columnData);
        JTable table=new JTable(tableModel);
        table.setFont(new Font("Times New Roman",Font.ITALIC,18));
        table.setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setFont(new Font("Times New Roman",Font.BOLD,20));
        table.setRowHeight(25);
        table.setRowMargin(5);
        
        JScrollPane scrollPane=new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane);

        frame.getContentPane().add(BorderLayout.CENTER,panel);
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent event){
                int result=JOptionPane.showConfirmDialog(frame,"Do you want to close ?", "Closing Dialogue Box", JOptionPane.OK_CANCEL_OPTION);
                if(result==JOptionPane.OK_OPTION) frame.dispose();
            }
        });
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        String filename="./data/data1.csv";
        ArrayList<ArrayList<String>> myList=gettingList(filename);
        HashMap<String,ArrayList<String>> myMap=gettingMap(myList);
        
        System.out.println(myList);
        System.out.println();
        System.out.println(myMap);

        showTable(myList);
    }
}
