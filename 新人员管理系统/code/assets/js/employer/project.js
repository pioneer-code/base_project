$(function () {
  var layer = layui.layer
  var form = layui.form

  initArtCateList()

  // 获取项目的列表
  function initArtCateList () {
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/project/select/company?company_name='+localStorage.getItem('company_name'),
      success: function (res) {
        var htmlStr = template('tpl-table', res)
        $('tbody').html(htmlStr)
      }
    })
  }

  // 为添加项目按钮绑定点击事件
  var indexAdd = null
  $('#btnAddCate').on('click', function () {
    indexAdd = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '添加新项目',
      content: $('#dialog-add').html()
    })
  })

  // 通过代理的形式，为 form-add 表单绑定 submit 事件
  $('body').on('submit', '#form-add', function (e) {
    e.preventDefault()
    $.ajax({
      method: 'POST',
      url: 'http://119.23.241.76:8000/employer/project/insert',
      data: $(this).serialize(),
      success: function (res) {
        if (res.insert !== 1)
        {
          return layer.msg('新增项目失败！')
        }
        initArtCateList()
        layer.msg('新增项目成功！')
        // 根据索引，关闭对应的弹出层
        layer.close(indexAdd)
      }
    })
  })
  
  // 通过代理的形式，为 btn-look 按钮绑定点击事件
  var indexEdit = null
  $('tbody').on('click', '.btn-look', function () {
    // 弹出一个查看项目信息的层
    indexEdit = layer.open({
      type: 1,
      area: ['800px', '400px'],
      title: '查看项目详情',
      content: $('#dialog-look').html()
    })

    var id = $(this).attr('data-id')
    // 发起请求获取对应分类的数据
    $.ajax({
      method: 'GET',
      url: 'http://119.23.241.76:8000/employer/project/select/department?company_name=软件开发科技有限公司&department_name=' + id,
      success: function (res) {
        form.val('form-edit', res.project[0])
      }
    })
  })

  // 通过代理的形式，为删除按钮绑定点击事件
  $('tbody').on('click', '.btn-delete', function () {
    var id = $(this).attr('data-id')
    var id_number = $(this).attr('data-number')
    // 提示用户是否要删除
    layer.confirm('确认删除?', { icon: 3, title: '提示' }, function (index) {
      $.ajax({
        method: 'GET',
        url: 'http://119.23.241.76:8000/employer/project/delete',
        data: {
          id: id,
          id_number: id_number
        },
        success: function (res) {
          if (res.delete !== 1)
          {
            return layer.msg('删除项目失败！')
          }
          layer.msg('删除项目成功！')
          layer.close(index)
          initArtCateList()
        }
      })
    })
  })
})
