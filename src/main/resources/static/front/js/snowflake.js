window.onload = function () {
    var flakColor = "#1bd3ff"; // 雪花颜色
    var flakContainer = $("<div></div>").css({position: "fixed", "top": "0", "pointer-events": "none"}); // 容器用于包裹雪花

    // 获取视图的高度和宽度
    var dhight = $(window).height();
    var dw = $(window).width() - 80;

    // 将容器添加到页面中
    flakContainer.appendTo($("body"));

    function generateSnowfall(size, density, duration) {
        // 生成密集的雪花
        for (var i = 0; i < density; i++) {
            var startLeft = Math.random() * dw;
            var startOpacity = 0.7 + Math.random() * 0.3;
            var endTop = dhight - 100;
            var endLeft = Math.random() * dw;

            // 创建雪花元素并添加到容器中
            var flak = $("<div></div>").css({
                "position": "absolute",
                "left": startLeft,
                "top": Math.random() * dhight,
                "opacity": startOpacity,
                "font-size": size,
                "color": flakColor
            }).html("❉").appendTo(flakContainer);

            // 设置雪花的动画效果
            flak.animate({
                "top": endTop,
                "left": endLeft,
                "opacity": 0.1
            }, duration, function () {
                // 在动画结束后，移除雪花元素
                $(this).remove();
            });
        }
    }

    // 暴风雪，瞬间生成大量雪花，持续一段时间
    generateSnowfall(30, 500, 1000);

    // 中雪，稍微减少数量
    setTimeout(function () {
        generateSnowfall(20, 300, 1000);
    }, 3500);

    // 小雪，更少数量
    setTimeout(function () {
        generateSnowfall(10, 100, 1000);
    }, 6000);
}
