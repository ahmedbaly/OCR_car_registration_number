/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ahmed.blank_project.ocr;

import android.util.Log;
import android.util.SparseArray;

import com.example.ahmed.blank_project.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple Processor which receives detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor implements Detector.Processor<TextBlock> {
    private ArrayList<String> arrayplaca = new ArrayList<>();
    private GraphicOverlay<OcrGraphic> mGraphicOverlay;

    private OnReciveDetectionListener onReciveDetectionListener;


//    Placa_model placa_model;
    public OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay, OnReciveDetectionListener onReciveDetectionListener) {
        mGraphicOverlay = ocrGraphicOverlay;
        this.onReciveDetectionListener = onReciveDetectionListener;
    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    private boolean filtroPlacaCarro(String src) {
        boolean matched = false;
        Pattern p = Pattern.compile("^[0-9]{3}(.?|-| )[0-9]{4}$");
        Matcher m = p.matcher(src);
        if (m.matches())
            matched = true;
        else if (!m.matches()) {
            p = Pattern.compile("^[0-9]{3}(.?|-| )[0-9]{3}$");
            m = p.matcher(src);
            if (m.matches())
                matched = true;
            else if (!m.matches()) {
                p = Pattern.compile("^[0-9]{3}(.?|-| )[0-9]{2}$");
                m = p.matcher(src);
                if (m.matches())
                    matched = true;
                else if (!m.matches()) {
                    p = Pattern.compile("^[0-9]{3}(.?|-| )[0-9]{1}$");
                    m = p.matcher(src);
                    if (m.matches())
                        matched = true;
                    else if (!m.matches()) {
                        p = Pattern.compile("^[0-9]{2}(.?|-| )[0-9]{4}$");
                        m = p.matcher(src);
                        if (m.matches())
                            matched = true;
                        else if (!m.matches()) {
                            p = Pattern.compile("^[0-9]{2}(.?|-| )[0-9]{3}$");
                            m = p.matcher(src);
                            if (m.matches())
                                matched = true;
                            else if (!m.matches()) {
                                p = Pattern.compile("^[0-9]{2}(.?|-| )[0-9]{2}$");
                                m = p.matcher(src);
                                if (m.matches())
                                    matched = true;
                                else if (!m.matches()) {
                                    p = Pattern.compile("^[0-9]{2}(.?|-| )[0-9]{1}$");
                                    m = p.matcher(src);
                                    if (m.matches())
                                        matched = true;
                                    else
                                        matched = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return matched;
    }

    private boolean tryPlacacarro2_3(String src ){
        Pattern p = Pattern.compile("^[0-9]{2}(.?|-| )[0-9]{3}$");
        Matcher m = p.matcher(src);
        if (m.matches())
            return true;
        else
            return false;
    }

    private boolean tryPlacacarro2_2(String src ){
        Pattern p = Pattern.compile("^[0-9]{2}(.?|-| )[0-9]{2}$");
        Matcher m = p.matcher(src);
        if (m.matches())
            return true;
        else
            return false;
    }



    private boolean filtroPlacaMoto(String src) {
        Pattern p = Pattern.compile("^[A-Z ]{3,5}(\\n)[0-9]{4}$");
        Matcher m = p.matcher(src);
        if (m.matches())
            return true;
        else
            return false;
    }

    private boolean isPlacaValida(String src) {
        Pattern p = Pattern.compile("^[A-Z]{3}[0-9]{4}$");
        Matcher m = p.matcher(src);
        if (m.matches())
            return true;
        else
            return false;
    }

//    private boolean shearchduplication(String placadetected){
//        boolean exist = false;
//        if (arrayplaca!=null && !arrayplaca.isEmpty())
//        {
//            for(int j=0;j<arrayplaca.size();j++){
//                if (arrayplaca.get(j).equals(placadetected))
//                {
//                    exist=true;
//                    break;
//                }
//            }
//        }
//        else {exist=false;}
//        return exist;
//    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        int j=0;
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);

            if(filtroPlacaCarro(item.getValue())) {
                Log.d("OcrCaptureActivity RD", item.getValue());
                String placa = limparPlaca(item.getValue());
                onReciveDetectionListener.onReciveDetection(placa);

                Log.d("OcrCaptureActivity AL", placa);

                OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);

                mGraphicOverlay.add(graphic);
//                if (!shearchduplication(placa)) {
//                    arrayplaca.add(placa);
//                }

            }
//            OcrCaptureActivity.DataList(arrayplaca);

        }

      //  DataList(arrayplaca);
    }

    public static String limparPlaca(String placa) {
        String placaCorrecao = placa;
        if (placaCorrecao.contains("-")) {
            placaCorrecao = placaCorrecao.replace("-", "");
        }
        if (placaCorrecao.contains(" ")) {
            placaCorrecao = placaCorrecao.replaceAll(" ", " تونس ");
        }
        if (placaCorrecao.contains(".")) {
            placaCorrecao = placaCorrecao.replace(".", "");
        }
        if (placaCorrecao.contains("\n")) {
            placaCorrecao = placaCorrecao.replace("\n", "");
        }

        return placaCorrecao;
    }

    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        mGraphicOverlay.clear();
    }

    public interface OnReciveDetectionListener{
        void onReciveDetection(String detection);
    }
}
