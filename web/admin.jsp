<%-- 
    Document   : admin
    Created on : May 27, 2023, 12:47:49 PM
    Author     : Duy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>  
    table, th, td {  
        border: 1px solid black;  
        border-collapse: collapse;  
        margin-top: 30px;
        margin-left: 50px;
    }  
    th, td {  
        padding: 10px;  
        text-align: center;
    }  
    table#alter tr:nth-child(even) {  
        background-color: #eee;  
    }  
    table#alter tr:nth-child(odd) {  
        background-color: #fff;  
    }  
    table#alter th {  
        color: white;  
        background-color: gray;  
    }  
    
    #class{
        margin-left: 50px;
        display: inline;
    }

    #class button{
        margin-left: 10px;
    }

    .h123{
        margin-top: 30px;
        margin-left: 50px;
    }

    </style>

<head>
    
    <meta charset="utf-8">
    <title>Bird Meal Order System</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport

    <!-- Favicon -->
    <link href=" img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins&family=Roboto:wght@700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

 
<header>
<!-- Topbar Start -->
<div class="container-fluid border-bottom d-none d-lg-block">
    <div class="row gx-0">
        <div class="col-lg-4 text-center py-2">
            <div class="d-inline-flex align-items-center">
                <i class="bi bi-geo-alt fs-1 text-primary me-3"></i>
                <div class="text-start">
                    <h6 class="text-uppercase mb-1">Địa Chỉ</h6>
                    <span>Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức</span>
                </div>
            </div>
        </div>
        <div class="col-lg-4 text-center border-start border-end py-2">
            <div class="d-inline-flex align-items-center">
                <i class="bi bi-envelope-open fs-1 text-primary me-3"></i>
                <div class="text-start">
                    <h6 class="text-uppercase mb-1">Email Us</h6>

                    <span>fpt@example.com</span>
                </div>
            </div>
        </div>
        <div class="col-lg-4 text-center py-2">
            <div class="d-inline-flex align-items-center">
                <i class="bi bi-phone-vibrate fs-1 text-primary me-3"></i>
                <div class="text-start">
                    <h6 class="text-uppercase mb-1">Call Us</h6>
                    <span>+123454654</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Topbar End -->


<!-- Navbar Start -->
<nav class="navbar navbar-expand-lg bg-white navbar-light shadow-sm py-3 py-lg-0 px-3 px-lg-0">
    <a href="Home.html" class="navbar-brand ms-lg-5">
        <h1 class="m-0 text-uppercase text-dark"><i class="bi bi-shop fs-1 text-primary me-3"></i>Bird Food Store
        </h1>
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        
        <div class="navbar-nav ms-auto py-0">
            <a href="Home.html" class="nav-item nav-link active">Home</a>
            <a href="blog.html" class="nav-item nav-link">Blog</a>
            <a href="cart.html" class="nav-item nav-link pt-3 "><i
                    class="bi bi-cart  fs-1 text-primary me-1"></i></a>
            <a href="" class="nav-item nav-link nav-contact bg-primary text-white px-5 ms-lg-5">Profile <i
                    class="bi bi-arrow-down"></i></a>
        </div>
    </div>
</nav>
<!-- Navbar End -->
</header>


<body>
    <h1 class="h123">Account list</h1>

    <div class="d-flex form-inputs" id="class">
        <input type="text" placeholder="Search any account...">
        <i class="bx bx-search"></i>
        <button>Search</button>
        <button>Add new account</button>
    </div>

    <div>
        <table border="1">
            <thead style="background: #7AB730; color: white">
                <tr>
                    <th>Account ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Address</th>
                    <th>Phone number</th>
                    <th>Role</th>
                    <th>Status</th>
                    <th>Action</th> 
                </tr>
            </thead>
            <tbody>
               
                    <tr>
                        <td>
                            <a href="index.html" class="nav-item nav-link active">1</a>
                        </td>
                        <td>admin</td>
                        <td>admin</td>
                        <td>admin@example.com</td>
                        <td>admin</td>
                        <td>hcm</td>
                        <td>123456</td>
                        <td>Admin</td>
                        <td>Active</td>
                        <td>
                            <button>Ban</button>
                            <button>Update</button>
                        </td>
                    </tr>
                
                    <tr>
                        <td>
                            <a href="index.html" class="nav-item nav-link active">2</a>
                        </td>
                        <td>staff</td>
                        <td>staff</td>
                        <td>staff@example.com</td>
                        <td>staff</td>
                        <td>hn</td>
                        <td>123123123</td>
                        <td>Staff</td>
                        <td>Active</td>
                        <td>
                            <button>Ban</button>
                            <button>Update</button>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="index.html" class="nav-item nav-link active">2</a>
                        </td>
                        <td>user1</td>
                        <td>user1</td>
                        <td>user1@example.com</td>
                        <td>user1</td>
                        <td>hn</td>
                        <td>1234545</td>
                        <td>User</td>
                        <td>Active</td>
                        <td>
                            <button>Ban</button>
                            <button>Update</button>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <a href="index.html" class="nav-item nav-link active">2</a>
                        </td>
                        <td>user2</td>
                        <td>user2</td>
                        <td>user2@example.com</td>
                        <td>user2</td>
                        <td>dn</td>
                        <td>17623467</td>
                        <td>User</td>
                        <td>Banned</td>
                        <td>
                            <button>Unban</button>
                            <button>Update</button>
                        </td>
                    </tr>

            </tbody>
        </table>
    </div>

</body>

</html>
