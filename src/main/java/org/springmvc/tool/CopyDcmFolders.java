package org.springmvc.tool;

import java.io.*;

/**
 * @ClassName CopyDcmFolders
 * @Author Bob
 * @Date 2019-06-02 18:09
 */
public class CopyDcmFolders {


    public static void copyFolder(File srcFile, File destFile) throws IOException {

        if(srcFile.isDirectory()){
            File newFolder=new File(destFile,srcFile.getName());
                newFolder.mkdirs();
                File[] fileArray=srcFile.listFiles();

                for(File file:fileArray){
                    copyFolder(file, newFolder);
                }


        }else if(srcFile.getName().endsWith("dcm")){
            //File newFile=new File(destFile,srcFile.getName());
            int dcmIndex = srcFile.getName().lastIndexOf(".");
            File newFile = new File(destFile,srcFile.getName().substring(0,dcmIndex)+".jpg");
            Dcm2jpgIOStreamOutput dcm2jpg = new Dcm2jpgIOStreamOutput();
            dcm2jpg.convert(srcFile, newFile);
            //copyFile(srcFile,newFile);
/*            try{
                for(int i = 1;i < 2;i++){
                    System.out.print("No: " + String.format("%06d", i) + "   Started  ");
                    String tempStr = String.format("%06d", i);
                    File src = new File("F:\\dicomfiles\\LIDC-IDRI-0827\\1.3.6.1.4.1.14519.5.2.1.6279.6001.728284743932342406301468721019\\1.3.6.1.4.1.14519.5.2.1.6279.6001.253322967203074795232627653819\\" + tempStr + ".dcm"); //这里我原本测试的是大概400个dcm文件，最后一次测试只实验了一张，这里填上dcm文件的具体位置就行。
                    File dest = new File("F:\\dicomfiles\\dcm2jpg\\" + tempStr + ".jpg");  //生成的JPEG图像位置
                    Dcm2jpgIOStreamOutput dcm2jpg = new Dcm2jpgIOStreamOutput();
                    dcm2jpg.convert(src, dest);
                    System.out.println("------>   Result:Completed");
                }
            }catch(IOException e){
                e.printStackTrace();

            }catch(Exception e){
                e.printStackTrace();
            }*/
        }

    }

    public static void removeDir(File file) {
        if(file.isFile())
        {
            file.delete();
        }else
        {
            File[] files = file.listFiles();
            if(files == null)
            {
                file.delete();
            }else
            {
                for (int i = 0; i < files.length; i++)
                {
                    removeDir(files[i]);
                }
                file.delete();
            }
        }
    }
    private static void copyFile(File srcFile, File newFile) throws IOException{
        // TODO Auto-generated method stub
        BufferedInputStream bis=new BufferedInputStream(new FileInputStream(srcFile));
        BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(newFile));

        byte[] bys=new byte[1024];
        int len=0;
        while((len=bis.read(bys))!=-1){
            bos.write(bys,0,len);
        }
        bos.close();
        bis.close();

    }



    public static void main(String[] args) throws IOException {

       File srcFile=new File("d:\\H0002T1855468\\1.2.840.113704.1.111.9148.1498844912.1");
       File destFile=new File("d:\\copy-H0002T1855468");
       copyFolder(srcFile,destFile);

    }
}
