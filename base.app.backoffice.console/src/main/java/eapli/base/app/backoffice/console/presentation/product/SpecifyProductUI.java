package eapli.base.app.backoffice.console.presentation.product;

import eapli.base.productmanagement.application.SpecifyProductController;

import eapli.base.productmanagement.dto.CategoryDto;

import eapli.base.warehousemanagement.dto.RowDTO;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



public class SpecifyProductUI extends AbstractUI {
    public boolean doShow() {
        SpecifyProductController controller = new SpecifyProductController();
        CategoryDto dto1;

        int n=0;
        for (CategoryDto dto:controller.getProductCategories()
        ) {
            n++;
            System.out.println(n +" " + dto.show() + " \n");
        }
        final Integer choice =  Console.readInteger("Choose one\n");

        dto1 = controller.getProductCategories().get(choice-1);

        final String internalCode =  Console.readLine("Internal Code\n");

        final String pc =  Console.readLine("Production Code\n");

        final String ref =  Console.readLine("Reference\n");

        final String b =  Console.readLine("Brand\n");

        final String sd =  Console.readLine("Short Description\n");

        final String ed =  Console.readLine("Extended Description\n");

        final String td =  Console.readLine("Technical Description\n");

        final double price =  Console.readDouble("Price\n");
        String location="";

            int x = 0;
            System.out.println("Location\n");
            for (RowDTO row : controller.showLocation()
            ) {
                for (int i = 0; i < row.getShelveQuantity(); i++) {
                    x++;
                    System.out.println(x + " " + row.toString() + " Shelve no : " + i);
                }
            }
            if (x != 0){
                final Integer choice1 = Console.readInteger("Choose one\n");

                RowDTO row = controller.showLocation().get(choice1 - 1);
                location = row.toString();
            }else {
                System.out.println("Empty Location so the default location will be to be defined\n");

                System.out.println("Location will set to default(default 0)");

                location = "default 0";

            }





        final String barcode = Console.readLine("Barcode\n");
        final String name = Console.readLine("Name\n");



            final String poto = Console.readLine("Photo\n");

            File file = new File(poto);
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            // int byteLength = fff.length();

            // In android the result of file.length() is long
            long byteLength = file.length(); // byte count of the file-content

            byte[] photo = new byte[(int) byteLength];
            try {
                fileInputStream.read(photo, 0, (int) byteLength);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        try {

            controller.specifyProduct(photo,internalCode,sd,ed,b,ref,pc,td,dto1,price,name,location,barcode);

        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a product which already exists in the database.");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Specify Product";
    }
}