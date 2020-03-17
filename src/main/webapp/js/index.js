function load() {
    isLogin(managerUrl + '/isLogin', '/login');
    loadPage();
    loadLastNotice();
    loadLastTwoComplain();
    loadEcharts();
}

function loadLastNotice() {
    $.get(noticeUrl + '/findLastNotice', notice => {
        $('.notice-content').html(notice.noticeContent);
    })
}

function loadLastTwoComplain() {
    $.get(complainUrl + '/findLastTwo', complains => {
        if (complains.length === 0) {

        } else {
            $.each(complains, (i, v) => {
                $('.complain-content').append('<p>' + v.complainContent + '</p>');
            })
        }
    })
}

function loadEcharts() {
    $.get(ownerUrl + '/getEchartsMap', map => {
        echarts.init(document.getElementById('owner-echarts')).setOption({
            title: {
                text: '业主性别分布',
                left: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b} : {c} ({d}%)'
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 10,
                top: 20,
                bottom: 20,
                data: map.gender,
            },
            series: [
                {
                    name: '姓名',
                    type: 'pie',
                    radius: '55%',
                    center: ['40%', '50%'],
                    data: map.map,
                    emphasis: {
                        itemStyle: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        });
    })
}