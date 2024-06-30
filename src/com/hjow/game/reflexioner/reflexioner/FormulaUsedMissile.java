/*
Copyright 2024 HJOW (hujinone22@naver.com)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.hjow.game.reflexioner.reflexioner;

import java.awt.geom.Point2D;

public interface FormulaUsedMissile
{
    double getMax_x();
    double getMax_y();
    double getMin_x();
    double getMin_y();
    int getDy();
    
    public Point2D getP1();
    public void setP1(Point2D p1);
    public Point2D getP2();
    public void setP2(Point2D p2);
    public double getProgress();
    public void setProgress(double progress);
    public double getMax_progress();
    public void setMax_progress(double max_progress);
    public String getFormula();
    public void setFormula(String formula);
    public double getRange_unit();
    public void setRange_unit(double range_unit);
    
    public boolean isRange_absolute();
    public void setRange_absolute(boolean range_absolute);
}
