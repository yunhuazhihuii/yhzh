<template>    
    <div class="list">
            <button v-for="txt in list"> <router-link to='/Cold'>{{ txt.message }} </router-link></button>
        </div> 

</template>

<script> 



export default{
    data(){
        return {               
            list: [
                { message: 'B4--6F' },
                { message: '07--16F' },
                { message: '17--27F ' },
                { message: '28--38F' },
                { message: '39--44F ' },
                { message: '45--50F' },
                { message: '51--53F ' }
            ]   
          
        }
    },

    methods:{

            // godatable(){
            //     this.$router.push({ name: 'ODatatable'})

            // }
        }       
}


    
</script>


<style scoped>




.list{
    width: 100px;
    position:absolute;
    left:330px;
    top:120px;
}

.list button{
  line-height:20px;
  width:100%;
  padding:5px;
  margin-top:15px;
  border:2px solid #aaa;
  border-radius:25px;
  box-shadow: 1px 1px 1px #aaa;
  color:#aaa;
    
}



</style>