$(function() {
  //预加载
  $('.employerLoginAndRegBox').hide()
  $('.reg-box').hide()

  // 点击“去注册账号”的链接
  $('#link_reg_employer').on('click', function() {
    $('.login-box-employer').hide()
    $('.reg-box-employer').show()
  })

  // 点击“去登录”的链接
  $('#link_login_employer').on('click', function() {
    $('.login-box-employer').show()
    $('.reg-box-employer').hide()
  })
  // 管理者点击“去注册账号”的链接
  $('#link_reg').on('click', function() {
    $('.login-box').hide()
    $('.reg-box').show()
  })

  // 管理者点击“去登录”的链接
  $('#link_login').on('click', function() {
    $('.login-box').show()
    $('.reg-box').hide()
  })
  // 点击“我是管理者”的链接
  $('#link_employer').on('click', function() {
    $('.employeeLoginAndRegBox').hide()
    $('.employerLoginAndRegBox').show()
    $('.login-box-employer').show()
    $('.reg-box-employer').hide()
  })
  $('#link_employerer').on('click', function() {
    $('.employeeLoginAndRegBox').hide()
    $('.employerLoginAndRegBox').show()
    $('.login-box-employer').show()
    $('.reg-box-employer').hide()
  })

  // 点击“我是用户”的链接
  $('#link_employee').on('click', function() {
    $('.employeeLoginAndRegBox').show()
    $('.employerLoginAndRegBox').hide()
  })
  $('#link_employeeee').on('click', function() {
    $('.employeeLoginAndRegBox').show()
    $('.employerLoginAndRegBox').hide()
  })

  // 从 layui 中获取 form 对象
  var form = layui.form
  var layer = layui.layer
  // 通过 form.verify() 函数自定义校验规则
  form.verify({
    // 自定义了一个叫做 pwd 校验规则
    pwd: [/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
    // 校验两次密码是否一致的规则
    repwd: function(value) {
      // 通过形参拿到的是确认密码框中的内容
      // 还需要拿到密码框中的内容
      // 然后进行一次等于的判断
      // 如果判断失败,则return一个提示消息即可
      var pwd = $('.reg-box [name=password]').val()
      if (pwd !== value) {
        return '两次密码不一致！'
      }
    }
  })
  //员工的请求
  // 监听注册表单的提交事件
  $('#form_reg').on('submit', function(e) {
    // 1. 阻止默认的提交行为
    e.preventDefault()
    // 2. 发起Ajax的POST请求
    // var data = {
    //   username: $('#form_reg [name=id_number]').val(),
    //   password: $('#form_reg [name=password]').val()
    // }
    var data = $('#form_reg').serialize()
    $.post({
      url: 'http://119.23.241.76:8000/employee/register', 
      data :data, 
      success:function(res) {
      // if (res.status !== 0) {
      //   return layer.msg(res.message)
      // }
        console.log(res);
        layer.msg('注册成功，请登录！')
        // 模拟人的点击行为
        $('#link_login').click()
      },
    })
  })

  // 监听登录表单的提交事件
  $('#form_login').submit(function(e) {
    // 阻止默认提交行为
    e.preventDefault()
    $.post({
      url: 'http://119.23.241.76:8000/employee/login',
      method: 'POST',
      // 快速获取表单中的数据
      data: $(this).serialize(),
      success: function(res) {
        // if (res.status !== 0) {
        //   return layer.msg('登录失败！')
        // }
        layer.msg('登录成功！')
        // 将登录成功得到的 token 字符串，保存到 localStorage 中
        localStorage.setItem('token', res.token)
        localStorage.setItem('id_number', $('#form_login [name=id_number]').val())
        // 跳转到后台主页
        location.href = './indexemployee.html'
      },
      error: function (res) {
        console.log(res);
        
      }
    })
  })

  //管理员的请求
  // 监听注册表单的提交事件
  $('#form_reg_employer').on('submit', function(e) {
    e.preventDefault()
    var data = $(this).serialize()
    console.log(data);
    $.post({
      url: 'http://119.23.241.76:8000/employer/register', 
      data :data, 
      success:function(res) {
      if (res.msg == '注册失败'||res.error == 'Internal Server Error') {
        return layer.msg('账号已存在')
      }
        console.log(res);
        layer.msg('注册成功，请登录！')
        // 模拟人的点击行为
        $('#link_login_employer').click()
      },
    })
  })

  // 监听登录表单的提交事件
  $('#form_login_employer').submit(function(e) {
    e.preventDefault()
    $.post({
      url: 'http://119.23.241.76:8000/employer/login/',
      method: 'POST',
      data: $(this).serialize(),
      success: function(res) {
        console.log(res);
        // if (res.status !== 0) {
        //   return layer.msg('登录失败！')
        // }
        console.log(res);
        layer.msg('登录成功！')
        localStorage.setItem('company_name', $('#form_login_employer [name=company_name]').val())
        // console.log($('#form_login_employer [name=company_name]').val());
        // localStorage.setItem('token', res.token)
        location.href = './indexemployer.html'
      }
    })
  })
})
