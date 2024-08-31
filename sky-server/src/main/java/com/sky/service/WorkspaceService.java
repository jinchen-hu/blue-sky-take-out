package com.sky.service;

import com.sky.vo.BusinessDataVO;
import com.sky.vo.DishOverViewVO;
import com.sky.vo.OrderOverViewVO;
import com.sky.vo.SetmealOverViewVO;
import java.time.LocalDateTime;

public interface WorkspaceService {

    /**
     * @param begin
     * @param end
     * @return
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * @return
     */
    OrderOverViewVO getOrderOverView();

    /**
     * @return
     */
    DishOverViewVO getDishOverView();

    /**
     * @return
     */
    SetmealOverViewVO getSetmealOverView();

}
