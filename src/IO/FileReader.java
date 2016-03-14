package IO;


import Geometry.*;
import Math.*;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by nekothecat on 2/22/16.
 */
public class FileReader {


    private static URL url = DibujarCasita3D.class.getResource("choco3D.txt");
    private static String fileName = url.getPath();
    private String line = null;

    private File file;

    private FileInputStream fis;

    private BufferedReader br;

    private List<Edge> listaAristas = new LinkedList<Edge>();
    private List<Vector4> listaPuntos = new LinkedList<Vector4>();
    private PolygonObject objeto = new PolygonObject();

    public FileReader() {

        try {
            fis = new FileInputStream(fileName);
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        leerObjeto();


    }

    private void leerObjeto() {

        int numeroPuntos = 0;

        String[] aristaStr = null;
        String[] punto = null;

        try {
            numeroPuntos = Integer.parseInt(br.readLine());
            for (int i = 0; i < numeroPuntos; i++) {
                punto = br.readLine().split(" ");
                listaPuntos.add(new Vector4(Double.parseDouble(punto[0]), Double.parseDouble(punto[1]), Double.parseDouble(punto[2])));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int numeroAristas = 0;
        try {
            numeroAristas = Integer.parseInt(br.readLine());

            for (int j=0; j < numeroAristas; j++) {

                aristaStr = br.readLine().split(" ");
                Vector4 punto1 = listaPuntos.get(Integer.parseInt(aristaStr[0]));
                Vector4 punto2 = listaPuntos.get(Integer.parseInt(aristaStr[1]));
//                arista[0] = Integer.parseInt(aristaStr[0]);
//                arista [1] = Integer.parseInt(aristaStr[1]);
                Edge arista = new Edge(punto1, punto2);
                listaAristas.add(arista);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List<Vector4> getPuntos() {
        return listaPuntos;
    }

    public List<Edge> getAristas() {
        return listaAristas;
    }

    public PolygonObject getObjeto() {
        for(Edge edge: listaAristas) {
            objeto.addEdge(edge);
        }
        return objeto;
    }

}
