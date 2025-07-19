const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootksp0l7ew/",
            name: "springbootksp0l7ew",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootksp0l7ew/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "New Energy Vehicle Sales System"
        } 
    }
}
export default base
