$(function () {
  var layer = layui.layer
  var form = layui.form

  initArtCateList()

  // 获取部门分类的列表
  function initArtCateList () {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/department/select/?department_name=&company_name='+localStorage.getItem('company_name')+'&random='+Math.random(),
      // data: localStorage.getItem('company_name'),
      dataType:"json", 
      success: function (res) {
        var htmlStr = template('tpl-table', res)
        $('tbody').html(htmlStr)
      }
    })
  }

  // 为添加类别按钮绑定点击事件
  var indexAdd = null
  $('#btnAddCate').on('click', function () {
    indexAdd = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '添加新部门',
      content: $('#dialog-add').html()
    })
    $('#bixuyizhi').val(localStorage.getItem('company_name'))
  })
  // 通过代理的形式，为 form-add 表单绑定 submit 事件
  $('body').on('submit', '#form-add', function (e) {
    e.preventDefault()
    $.ajax({
      method: 'POST',
      data: $(this).serialize(),
      url: 'http://119.23.241.76:8000/employer/department/insert',
      success: function (res) {
        if (res.status === 500)
        {
          return layer.msg('新增部门失败！')
        }
        if (res.insert === 1) {
          layer.msg('新增部门成功！')
        initArtCateList()
        // 根据索引，关闭对应的弹出层
        layer.close(indexAdd)
        }
      }
    })
  })

  // 通过代理的形式，为 btn-edit 按钮绑定点击事件
  var indexEdit = null
  $('tbody').on('click', '.btn-edit', function () {
    // 弹出一个修改部门分类信息的层
    indexEdit = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '修改部门详情',
      content: $('#dialog-edit').html()
    })
    var id = $(this).attr('data-id')
    console.log(id);
    // 发起请求获取对应分类的数据
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/department/select_one/?department_name=' +id+ '&company_name='+localStorage.getItem('company_name'),
      success: function (res) {
        form.val('form-edit', res.department)
      }
    })
  })
  // 通过代理的形式，为 btn-look 按钮绑定点击事件
  var indexEdit = null
  $('tbody').on('click', '.btn-look', function () {
    // 弹出一个查看部门分类信息的层
    indexEdit = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '查看部门详情',
      content: $('#dialog-look').html()
    })

    var id = $(this).attr('data-id')
    // 发起请求获取对应分类的数据
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/department/select_one/?department_name=' +id+ '&company_name='+localStorage.getItem('company_name'),
      success: function (res) {
        form.val('form-edit', res.department)
      }
    })
  })

  // 通过代理的形式，为修改分类的表单绑定 submit 事件
  $('body').on('submit', '#form-edit', function (e) {
    e.preventDefault()
    var data = {
      company_name : $('.layui-form [name=companyName]').val(),
      department_name : $('.layui-form [name=departmentName]').val(),
      plan_amount : $('.layui-form [name=planAmount]').val(),
      already_amount : $('.layui-form [name=alreadyAmount]').val(),
      requirements : $('.layui-form [name=requirements]').val()
    }
    $.ajax({
      method: 'POST',
      url: 'http://119.23.241.76:8000/employer/department/update',
      data: data,
      success: function (res) {
        if (res.update !== 1)
        {
          return layer.msg('更新部门数据失败！')
        }
        layer.msg('更新部门数据成功！')
        layer.close(indexEdit)
        initArtCateList()
      }
    })
  })

  // 通过代理的形式，为删除按钮绑定点击事件
  $('tbody').on('click', '.btn-delete', function () {
    var id = $(this).attr('data-id')
    // 提示用户是否要删除
    layer.confirm('确认删除?', { icon: 3, title: '提示' }, function (index) {
      $.ajax({
        method: 'GET',
        url: 'http://119.23.241.76:8000/employer/department/delete/?company_name=' + localStorage.getItem('company_name')+'&department_name='+id,
        success: function (res) {
          if (res.delete !== 1)
          {
            return layer.msg('删除部门失败！')
          }
          initArtCateList()
          layer.msg('删除部门成功！')
          layer.close(index)
        }
      })
    })
  })
})
