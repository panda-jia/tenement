/* --- 页面组件加载 --- */

function loadPage() {
    $('.layui-header').load('/pages/public/header.html');
    $('.layui-side').load('/pages/public/menu.html');
    layui.use('element', () => {
    })
}

// loadPage();

/* --- --- */

/* --- 页面跳转 --- */

let global_page = 1,
    global_limit = 10,
    ownerUrl = '/owner',
    buildingUrl = '/building',
    houseUrl = '/house',
    costUrl = '/cost',
    noticeUrl = '/notice',
    repairUrl = '/repair',
    complainUrl = '/complain',
    parkingUrl = '/parking',
    adminUrl = '/admin',
    managerUrl = '/manage',
    logoutUrl = '/logout',
    topLength = 30,
    timeout = 300;

let urlArray = {
    index: () => jumpPage('/home'),
    owner: () => jumpPage(ownerUrl),
    building: () => jumpPage(buildingUrl),
    house: () => jumpPage(houseUrl),
    cost: () => jumpPage(costUrl),
    notice: () => jumpPage(noticeUrl),
    repair: () => jumpPage(repairUrl),
    complain: () => jumpPage(complainUrl),
    parking: () => jumpPage(parkingUrl),
    logout: () => jumpPage(logoutUrl),
    changePwd: () => jumpPage('/change'),
    admin: () => jumpPage(adminUrl),
    manager: () => jumpPage(adminUrl + managerUrl),
    adminLogout: () => jumpPage(adminUrl + logoutUrl),
    adminChange: () => jumpPage(adminUrl + '/change')
};

function jumpPage(pageUrl) {
    window.location.href = pageUrl;
}

/* --- --- */

/* --- 自定义模态框 --- */

function showLayout(elem) {
    elem.children('.layout-background').removeClass('display-none').addClass('background-fixed', timeout);
    elem.children('.layout-box').removeClass('display-none').addClass('box-fixed', timeout);
}

function hideLayout(elem) {
    elem.children('.layout-background').removeClass('background-fixed', timeout);
    elem.children('.layout-box').removeClass('box-fixed', timeout);
    setTimeout(() => {
        elem.find('.layout-background, .layout-box').addClass('display-none');
    }, timeout);
}

/* --- --- */

/* --- 正则 --- */

let regex = {
    phone: phone => {
        return /^1\d{10}$/ig.test(phone);
    },
    email: email => {
        return /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/ig.test(email);
    },
    cardNo: cardNo => {
        return /^\d{6}(18|19|20)\d{2}(0\d|10|11|12)([0-2]\d|30|31)\d{3}[\dXx]$/ig.test(cardNo);
    },
    qq: qq => {
        return /^[1-9][0-9]{4,10}$/ig.test(qq);
    },
    decimal: number => {
        return /^\d{1,9}(\.{0}|\.\d{1,2})?$/ig.test(number);
    },
    isNumber: number => {
        return /^\d+$/ig.test(number);
    },
    isCarNumber: carNumber => {
        return /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z][A-Z][A-Z0-9]{4}[A-Z0-9挂学警港澳]$/ig.test(carNumber);
    }
};

/* --- --- */

/* --- 自定义函数 --- */

function formatDate(date) {
    let y = date.getFullYear();
    let M = date.getMonth() + 1;
    let d = date.getDate();
    let H = date.getHours();
    let m = date.getMinutes();
    let s = date.getSeconds();
    return y + '-' + (M < 10 ? ('0' + M) : M) + '-' + (d < 10 ? ('0' + d) : d) + " " + (H < 10 ? ('0' + H) : H) + ":" + (m < 10 ? ('0' + m) : m) + ":" + (s < 10 ? ('0' + s) : s);
}

function isLogin(url, loginUrl) {
    $.get(url, elem => {
        console.info(elem);
        if (elem === '') {
            jumpPage(loginUrl);
        } else {
            if (url === '/admin/isLogin') {
                $('.layui-nav').find('img').attr('alt', 'admin');
                $('.layui-nav span').attr('title', elem.adminId);
                $('.layui-nav a span').html(elem.account);
            } else {
                $('.layui-nav').find('img').attr('alt', 'manager');
                $('.layui-nav span').attr('title', elem.empId);
                $('.layui-nav a span').html(elem.empName);
            }
        }
    })
}

/* --- --- */