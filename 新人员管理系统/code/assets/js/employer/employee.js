$(function () {
  var layer = layui.layer
  var form = layui.form

  initArtCateList()

  // 获取员工的列表
  function initArtCateList () {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/staff/select/',
      data: {'company_name': localStorage.getItem('company_name')},
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
      title: '添加新员工',
      content: $('#dialog-add').html()
    })
    $('#bixuyizhi').val(localStorage.getItem('company_name'))
  })

  // 通过代理的形式，为 form-add 表单绑定 submit 事件
  $('body').on('submit', '#form-add', function (e) {
    e.preventDefault()
    $.ajax({
      method: 'POST',
      url: 'http://119.23.241.76:8000/employer/staff/insert',
      data: $(this).serialize(),
      success: function (res) {
        if (res.msg == '注册失败,账户已存在')
        {
          return layer.msg('新增员工失败！')
        }
        initArtCateList()
        layer.msg('新增员工成功！')
        // 根据索引，关闭对应的弹出层
        layer.close(indexAdd)
      }
    })
  })

  // 通过代理的形式，为 btn-edit 按钮绑定点击事件
  var indexEdit = null
  $('tbody').on('click', '.btn-edit', function () {
    // 弹出一个修改员工信息的层
    indexEdit = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '修改员工详情',
      content: $('#dialog-edit').html()
    })
    $('#bixuyizhi').val(localStorage.getItem('company_name'))
    var id = $(this).attr('data-id')
    $('#sfzh').val(id)
  })
  // 通过代理的形式，为 btn-look 按钮绑定点击事件
  var indexEdit = null
  $('tbody').on('click', '.btn-look', function () {
    // 弹出一个查看员工信息的层
    indexEdit = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '查看员工详情',
      content: $('#dialog-look').html()
    })

    var id = $(this).attr('data-id')
    // 发起请求获取对应分类的数据
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employee/select_infomation?id_number=' + id,
      success: function (res) {
        form.val('form-edit', res.staff)
      }
    })
  })

  // 通过代理的形式，为修改员工的表单绑定 submit 事件
  $('body').on('submit', '#form-edit', function (e) {
    e.preventDefault()
    $.ajax({
      method: 'POST',
      url: 'http://119.23.241.76:8000/employer/staff/update',
      data: $(this).serialize(),
      success: function (res) {
        if (res.update !== 1)
        {
          return layer.msg('更新员工数据失败！')
        }
        layer.msg('更新员工数据成功！')
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
        url: 'http://119.23.241.76:8000/employer/staff/delete/?id_number=' + id,
        success: function (res) {
          if (res.delete !== 1)
          {
            return layer.msg('删除员工失败！')
          }
          layer.msg('删除员工成功！')
          layer.close(index)
          initArtCateList()
        }
      })
    })
  })
})
