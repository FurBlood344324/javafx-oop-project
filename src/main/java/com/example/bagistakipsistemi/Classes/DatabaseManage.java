package com.example.bagistakipsistemi.Classes;
import java.io.*;
import java.util.ArrayList;
import java.nio.file.Paths;

public class DatabaseManage {
    private final String Data_path = Paths.get(
            System.getProperty("user.dir"), "src", "main", "java", "resources", "com", "example", "bagistakipsistemi", "Data.txt"
    ).toString();

    public DatabaseManage() {
    }

    public void Save_to_data(Data data){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Data_path, true));
            if(data instanceof IndividualUser){
                String dataRecord = String.join(";", ((IndividualUser) data).getName(), ((IndividualUser) data).getSurname(),
                        ((IndividualUser) data).getEmail(), Integer.toString(((IndividualUser) data).getTelephonenumber()), ((IndividualUser) data).getNickname(),
                        ((IndividualUser) data).getPassword(), "individualuser");
                writer.write(dataRecord);
            }
            else if(data instanceof InstutionalUser){
                String dataRecord = String.join(";", ((InstutionalUser) data).getInstitutionName(), ((InstutionalUser) data).getIBANNumber(),
                        ((InstutionalUser) data).getExplanation(), ((InstutionalUser) data).getNickname(), ((InstutionalUser) data).getEmail(),
                        ((InstutionalUser) data).getPassword(), "instutionaluser");
                writer.write(dataRecord);
            }
            else if(data instanceof Donate){
                String dataRecord = String.join(";", ((Donate) data).getSenderName(), ((Donate) data).getSenderSurname(),
                        ((Donate) data).getInstutionName(), ((Donate) data).getDonateType(), ((Donate) data).getSpecialDonateType(),
                        Boolean.toString(((Donate) data).getIsAnonym()), "donate");
                writer.write(dataRecord);
            }
            writer.newLine();
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }

    public ArrayList<Data> Read_data(){
        ArrayList<Data> datas = new ArrayList<Data>();
        try{
            ArrayList<String[]> rawdatas = new ArrayList<String[]>();
            BufferedReader reader = new BufferedReader(new FileReader(Data_path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";", 7);
                rawdatas.add(data);
            }
            reader.close();
            for (String[] data : rawdatas) {
                if(data[6].equals("individualuser")){
                    datas.add(new IndividualUser(data[0], data[1], Integer.parseInt(data[3]), data[4], data[2], data[5]));
                }
                else if(data[6].equals("instutionaluser")){
                    datas.add(new Donate(data[0], data[1], data[2], data[3], data[4], Boolean.parseBoolean(data[5])));
                }
                else if(data[6].equals("donate")){
                    datas.add(new InstutionalUser(data[0], data[1], data[3], data[4], data[2], data[5]));
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
        }
        return datas;
    }
}