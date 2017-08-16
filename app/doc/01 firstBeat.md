
运动负荷底层上报



 6. firstbeat
    a. 实时的TE @guojing @machenglin p2

    // 界面
    b. 体能状态: 运动中6分钟固定提醒 UI已提供。@lihongming 增加klvp通知 @jinliang UI增加  p0(7.28)
                       绘制规则 @gaomingying UI @liguangsong GUI @jinliang Android实现       p1


    c. 卡路里新增firstbeat计算卡路里  p1 (开发下周完成v1)

     // 历史记录中
    d. TD(运动负荷) weekly（4个INT） @lihongming 增加每天的klvp通知 @jinliang 增加记录
@zhoujiangtao 增加云端存储       p2


    e. 实时运动指导(30+个通知)       p1
         // 运动目标
         e1. 运动目标 @lihongming proto done @lihongming mcu 修改 @jinliang 发送到mcu              p0
         e2. 通知上报 @lihongming 增加Action @gaomingying UI @liguangsong GUI @jinliang 开发 p1

     // 下一项运动推荐
     f. 下一项运动推荐 @gaomingying UI(文案、展示规则) @lihongming firstbeat算法 proto定义         
@jinliang Android层实现                                         p1 
     g. "?"号的 说明 @gaomingying UI(文案)             p2




  /**
     * 
     * @param weeklyTrainLoadSum 当前设置的数值
     * @param minValue  最小数值分割线
     * @param maxValue 最大数值分割线
     * @param overReachValue over 数值分割线
     */