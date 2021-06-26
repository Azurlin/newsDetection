<template>
    <section>
        <div>
          <el-card class="box-card">
              <div>
                <el-row>
                  <el-col :span="24">
                    <div>
                        新闻来源：
                        <el-select v-model="value" placeholder="请选择新闻来源" style="width:180px">
                          <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                          </el-option>
                      </el-select>
                    </div>
                  </el-col>
                </el-row>

                <el-row style="padding-top:5px;">
                  <el-col :span="24">
                    <div>
                        数量单位：
<el-input-number v-model="num"  @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
                    </div>
                  </el-col>
                </el-row>

                <el-row style="padding-top:5px;">
                  <el-col :span="24">
                    <div>
                        <el-button type="primary" @click="getNewsByC">获取</el-button>
                    </div>
                  </el-col>
                </el-row>
                                 

              </div>
          </el-card>
        </div>
    </section>
</template>

<script>
import {
getNewsByCrawler
} from "../../../api/api";

export default {
  data() {
    return {
      options:[{ value: 1, label: 'BuzzFeed' },],
       num: 1,
       value:1,
    };
  },
  methods: {
  getNewsByC(){
    let params = {
      count:this.value
    };
    getNewsByCrawler(this.value);
  },

  }
};
</script>

<style scoped>
.box-card{
  margin-bottom: 5px;
}
</style>