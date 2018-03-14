//var app = angular.module('myApp', [ 'ngRoute','ngCookies','ngFileUpload' ]);
var app = angular.module('myApp', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'home/main.html'
		
	})

	.when('/manageUser', {
		templateUrl : 'admin/manage_users.html',
		controller : 'AdminController'
	})

	.when('/event', {
		templateUrl : 'upload/upload.html',
		controller : 'FileUploadController'
	})


	.when('/login', {
		templateUrl : 'user/login.html',
		controller : 'UserController'
	})

	.when('/logout', {
		templateUrl : 'user/login.html',
		controller : 'UserController'
	})

	
	.when('/reg', {
		templateUrl : 'user/register.html',
		controller : 'UserController'
	})
	
	.when('/myProfile', {
		templateUrl : 'user/my_profile.html',
		controller : 'UserController'
	})

	/**
	 * Blog related mapping
	 */

	.when('/Blog', {
		templateUrl : 'blog/BlogAdd.html',
		controller : 'BlogController'
	})

	.when('/BlogView', {
		templateUrl : 'blog/BlogView.html',
		controller : 'BlogController'
	})

	.when('/Job', {
		templateUrl : 'job/frmJob.html',
		controller : 'JobController'
	})

	.when('/JobList', {
		templateUrl : 'job/frmListJob.html',
		controller : 'JobController'
	})

	.when('/JobApply', {
		templateUrl : 'job/frmViewMyJobApply.html',
		controller : 'JobController'
	})


	.when('/add_friend', {
		templateUrl : 'friend/add_friend.html',
		controller : 'FriendController'
	})

	.when('/search_friend', {
		templateUrl : 'friend/search_friend.html',
		controller : 'FriendController'
	})

	.when('/view_friend', {
		templateUrl : 'friend/view_friend.html',
		controller : 'FriendController'
	})
	
	.when('/chat', {
		templateUrl : 'chat/ChatView.html',
		controller : 'ChatController'
	})

	.when('/Forum', 
	{
		templateUrl : 'forum/frmForum.html',
		controller : 'ForumController'
	})

	.when('/ForumView', 
	{
		templateUrl : 'forum/frmUserForumAdmin.html',
		controller : 'ForumController'
	})

	.when('/listForum', 
	{
		templateUrl : 'forum/frmListForum.html',
		controller : 'ForumController'
	})

	
	.when('/EditForum/:id', 
	{
		templateUrl : 'forum/ForumEdit.html',
		controller : 'ForumController'
	})

		
	.when('/create_chat_forum', {
		templateUrl : 'chat_forum/create_chat_forum.html',
		controller : 'ChatForumController'
	})

	.when('/list_chat_forum', {
		templateUrl : 'chat_forum/list_chat_forum.html',
		controller : 'ChatForumController'
	})

	.when('/view_chat_forum', {
		templateUrl : 'chat_forum/view_chat_forum.html',
		controller : 'ChatForumController'
	})
.when('/profilepic',{
		templateUrl:'views/profilepic.html'
	
	})
	
	.otherwise({
		redirectTo : '/'
	});
});

app.run( function ($rootScope, $location,$cookieStore, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		 //http://localhost:8089/Collab/addjob
	        // redirect to login page if not logged in and trying to access a restricted page
	        var restrictedPage = $.inArray($location.path(), ['//','/home.html','/','/search_job','/view_blog','/login', '/reg','/list_blog']) === -1;
	        
	        console.log("restrictedPage:" +restrictedPage)
	        var loggedIn = $rootScope.currentUser.id;
	        $rootScope.username = loggedIn;
	        console.log("loggedIn:" +loggedIn+" "+$rootScope.username )
	        
	        if(!loggedIn)
	        	{
	        	
	        	 if (restrictedPage) {
		        	  console.log("Navigating to login page:")
		        	

						            $location.path('/login');
		                }
	        	}
	        
			 else //logged in
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 var userRestrictedPage = $.inArray($location.path(), ["/post_job"]) == 0;
				 
				 if(userRestrictedPage && role!='admin' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					 
					 }
				     
	        	
	        	}
	        
	 }
	       );
	 
	 
	 // keep user logged in after page refresh
     $rootScope.currentUser = $cookieStore.get('currentUser') || {};
    
     if ($rootScope.currentUser) {
         $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.currentUser; 
     }

});


 
    
    
