/**
* Universidad Del Valle de Guatemala
* 23-nov-2015
* Pablo Díaz 13203
*/

package miniga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Pablo
 */
public class AlgoritmoGenetico {
    
    public void procesoGenetico(){
     // TODO code application logic here
        System.out.println(f1(12,9));
        ArrayList<Individuo> poblacion = new ArrayList();
        Random random = new Random();
        //realizar una poblacion inicial de 50 individuos
        //con cromosomas aleatorios
        for (int i = 0;i<500;i++){
            Integer[] result = genRandom();
            poblacion.add(new Individuo(result[0],result[1]));
        }
        int iteraciones = 100;
        while (!poblacion.isEmpty()){
             double acumFit = 0;
             //Paso 1.1Se calcula la fitness Total
             for (int j = 0;j<poblacion.size();j++){
                 acumFit += f1(poblacion.get(j).getCromosomaX(),poblacion.get(j).getCromosomaY());
             }
              double acum = 0;
             //Paso 1.2. Se vuelve a calcular la fitness individual y se normaliza
             for (int j = 0;j<poblacion.size();j++){
                 double fit = f1(poblacion.get(j).getCromosomaX(),poblacion.get(j).getCromosomaY());
                 acum += fit;
                 double fit2 = fit/acumFit;
                 
                 poblacion.get(j).setFitness(fit2);

             }
             //Paso 2. Sort in descending order fitness
             Collections.sort(poblacion,Collections.reverseOrder());
             
             //Paso 3. Fitness acumulada para cada individuo
              for (int j = 0;j<poblacion.size();j++){
                poblacion.get(j).setAcumFitness(acumHasta(poblacion,j));
            }
              
              
              System.out.println("");
              //Paso 4.0 Seleccionar los 2 padres individuos más fit
              ArrayList<Individuo> seleccionados = new ArrayList();
              for (int j = 0;j<2;j++){
                double R = random.nextDouble();//4.1 Random entre 0 y 1.
                for (int i = 0;i<poblacion.size();i++){
                   //4.2 seleccionar el primero que tenga la acumulada mayor que R
                    if (poblacion.get(i).getAcumFitness()>R){
                        seleccionados.add(poblacion.get(i));
                        if (poblacion.get(i).getFitness()<=(2.0/poblacion.size()))
                            poblacion.remove(poblacion.get(i));
                        break;
                    }
                }
              }
              
              //Ciclo para escoger un hijo de cada 2 seleccionados
              ArrayList<Individuo> nuevaPoblacion = new ArrayList();
              for (int j = 0;j+2<=seleccionados.size();j=j+2){
                  double pc = random.nextDouble();
                  Individuo nuevo = new Individuo();
                  int cont = 2;
                  if (pc>=0.5){
                    nuevo.setCromosomaX(seleccionados.get(j).getCromosomaX());
                    nuevo.setCromosomaX(seleccionados.get(++j).getCromosomaY());
                  }
                  else{
                 nuevo.setCromosomaX(seleccionados.get(++j).getCromosomaX());
                 nuevo.setCromosomaX(seleccionados.get(j).getCromosomaY()); 
                  }
                 nuevaPoblacion.add(nuevo);
                 
              }
              System.out.println(nuevaPoblacion.size());
              int r = random.nextInt(nuevaPoblacion.size()+1);
              for (int j = 0;j<nuevaPoblacion.size();j++){
                  if (r == j){
                      Integer[] result = genRandom();
                      nuevaPoblacion.get(j).setCromosomaX(result[0]);
                      nuevaPoblacion.get(j).setCromosomaY(result[1]);
                  }
                  
              }
             
            
              poblacion.addAll(nuevaPoblacion);
              //System.out.println(poblacion);
               iteraciones--;
              if (iteraciones == 0)
                  break;
              
             
           
             
        }
        System.out.println("");
        System.out.println("");
         System.out.println(poblacion);
       // System.out.println(poblacion);
        System.out.println(mejor(poblacion));
        
    }
    
    
    public double f1(int x1,int x2){
        return 3*x1+5*x2;
        //return 15*x1+30*x2+4*x1*x2-2*Math.pow(x1,2)-4*Math.pow(x2,2);
    }
    public double acumHasta(ArrayList<Individuo> num, int hasta){
        double returnNum = 0;
        for (int i = 0;i<=hasta;i++){
            returnNum += num.get(i).getFitness();
        }
        return returnNum;
    }
    
    public Integer[] genRandom(){
        Integer[] result = new Integer[2];
        Random random = new Random();
        int x1 = random.nextInt(5);
        int x2 = random.nextInt(7);
        while (true){
           
           // System.out.println(Math.abs(30-(x1+2*x2)));
            x1 = random.nextInt(10);
            x2 = random.nextInt(10);
            if ((x1<=4) && (2*x2<=12)&&(3*x1+2*x2)<=18){
               break;
           }
           
        }
        System.out.println(x1<4);
        System.out.println(2*x2<12);
        System.out.println(3*x1+2*x2<=18);
        result[0] = x1;
        result[1] = x2;
        return result;
    }
    
    public Individuo mejor(ArrayList<Individuo> arrayI){
        int j  = 0;
        double mayor = 0;
        for (int i=0;i<arrayI.size();i++){
            int x = arrayI.get(i).getCromosomaX();
            int y = arrayI.get(i).getCromosomaY();
            if (x == 2 && y==6)
                System.out.println("x");
            double tempMayor = f1(x,y);
            if (tempMayor>mayor){
                j = i;
                mayor = tempMayor;
            }
            
            
         
        }
        
        System.out.println(mayor);
        return arrayI.get(j);
        
    }

}
