package com.example.bagistakipsistemi.Classes;
import java.io.*;
import java.util.ArrayList;
import java.nio.file.Paths;

public class DatabaseManage {
    private final String Data_path = Paths.get(
            System.getProperty("user.dir"), "src", "main", "resources", "Data.txt"
    ).toString();


    public DatabaseManage() {
    }

    public void Save_to_data(Data data) throws IOException {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Data_path, true));
            if(data instanceof IndividualUser){
                String dataRecord = String.join(";", ((IndividualUser) data).getName(), ((IndividualUser) data).getSurname(),
                        Integer.toString(((IndividualUser) data).getTelephonenumber()), ((IndividualUser) data).getNickname(),
                        ((IndividualUser) data).getEmail(), ((IndividualUser) data).getPassword(), "individualuser");
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
            throw new IOException();
        }
    }

    public ArrayList<Data> Read_data() throws IOException {
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
                    datas.add(new IndividualUser(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5]));
                }
                else if(data[6].equals("instutionaluser")){
                    datas.add(new Donate(data[0], data[1], data[2], data[3], data[4], Boolean.parseBoolean(data[5])));
                }
                else if(data[6].equals("donate")){
                    datas.add(new InstutionalUser(data[0], data[1], data[2], data[3], data[4], data[5]));
                }
            }
        }
        catch(IOException e){
            System.out.println("Error");
            throw new IOException();
        }
        return datas;
    }

    public void Delete_data(Data data1, ArrayList<Data> datas) throws IOException {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Data_path));
            for (Data data : datas){
                if(data instanceof IndividualUser){
                    if(data1 instanceof IndividualUser){
                        if(((IndividualUser) data1).getEmail().equals(((IndividualUser) data).getEmail()))
                            continue;
                    }
                    String dataRecord = String.join(";", ((IndividualUser) data).getName(), ((IndividualUser) data).getSurname(),
                            Integer.toString(((IndividualUser) data).getTelephonenumber()), ((IndividualUser) data).getNickname(),
                            ((IndividualUser) data).getEmail(), ((IndividualUser) data).getPassword(), "individualuser");
                    writer.write(dataRecord);
                    writer.newLine();
                }
                else if(data instanceof InstutionalUser){
                    if(data1 instanceof InstutionalUser){
                        if(((InstutionalUser) data1).getEmail().equals(((InstutionalUser) data).getEmail()))
                            continue;
                    }
                    String dataRecord = String.join(";", ((InstutionalUser) data).getInstitutionName(), ((InstutionalUser) data).getIBANNumber(),
                            ((InstutionalUser) data).getExplanation(), ((InstutionalUser) data).getNickname(), ((InstutionalUser) data).getEmail(),
                            ((InstutionalUser) data).getPassword(), "instutionaluser");
                    writer.write(dataRecord);
                    writer.newLine();
                }
                else if(data instanceof Donate){
                    String dataRecord = String.join(";", ((Donate) data).getSenderName(), ((Donate) data).getSenderSurname(),
                            ((Donate) data).getInstutionName(), ((Donate) data).getDonateType(), ((Donate) data).getSpecialDonateType(),
                            Boolean.toString(((Donate) data).getIsAnonym()), "donate");
                    writer.write(dataRecord);
                    writer.newLine();
                }
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error");
            throw new IOException();
        }
    }

    public void Clear_data() throws IOException {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(Data_path));
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error");
            throw new IOException();
        }
    }
}