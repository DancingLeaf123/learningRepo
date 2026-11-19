# AI 协作说明（backend-playground）

## 学习模式

1. **分步推进**：按第 N 步小步做，每步有验收（`mvnw test` / 浏览器 / H2 Console）。
2. **默认你自己写代码**；只有你说「帮我改」时 AI 才直接改仓库。
3. **沟通要短**：问「下一步学什么」只答一步；要细节时说「继续做第 N 步」。

## 每次实现新代码前，AI 必须先讲清

| 部分 | 内容 |
|------|------|
| **Why** | 为什么要做（解决什么问题） |
| **What** | 本步改哪些文件、接口行为变成什么样 |
| **How（概要）** | 关键注解/类，不展开无关概念 |

然后再给 **可粘贴的完整示例代码**（整文件或清晰片段），不是零散 diff。

## 进度记录

只看 [`PROGRESS.md`](./PROGRESS.md)：仅保留 **当前这一步** 的目标与状态。历史步骤不写长文档。

## 项目入口

- 入口：`BackendPlaygroundApplication`
- 端口：`8081`（`application.yml`）
- 构建：`./mvnw test`

## Boot 4 备忘（踩坑时查）

- MyBatis：`mybatis-spring-boot-starter` **4.0.0**
- H2 Console：依赖 `spring-boot-h2console`
- MockMvc：`org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc`