<template>

<v-data-table
    :headers="headers"
    :items="mypage"
    :items-per-page="5"
    class="elevation-1"
  ></v-data-table>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'Mypage',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        headers: [
            { text: "id", value: "id" },
            { text: "customerid", value: "customerid" },
            { text: "rentid", value: "rentid" },
            { text: "bookid", value: "bookid" },
            { text: "bookName", value: "bookName" },
            { text: "price", value: "price" },
            { text: "point", value: "point" },
            { text: "rentStatus", value: "rentStatus" },
        ],
        mypage : [],
    }),
    async created() {
      var temp = await axios.get(axios.backend + '/mypages')

      this.mypage = temp.data._embedded.mypages;

    },
    methods: {
    }
  }
</script>

