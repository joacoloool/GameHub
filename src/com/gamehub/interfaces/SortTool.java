package com.gamehub.interfaces;

/** *
 * Interfaz generica donde ordenamos colecciones
 * */
public interface SortTool <T>{
    /** * Ordenar los elementos por nombre */
    public void sortName();
    /** * Ordenar los elementos por favorito*/
    public void sortFavorite();
    /** * Ordenar los elementos por la ultima vez que jugaron */
    public void sortLastTime();
    /** * Ordenar los elementos por la cantidad de veces que se jugaron*/
    public void sortPlayCount();
}
