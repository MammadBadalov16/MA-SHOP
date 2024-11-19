package az.mb.shop.domain.use_case.get_category

import android.util.Log
import az.mb.shop.common.Resource
import az.mb.shop.data.mapper.toCategory
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val remoteRepository: RemoteRepository) {


    operator fun invoke(): Flow<Resource<List<Category>>> = channelFlow {
        try {
            send(Resource.Loading())
            val categories = remoteRepository.getCategories().toCategory()
            Log.e("Get caregories success ", categories.toString())
            send(Resource.Success(data = categories))
        } catch (e: Exception) {
            Log.e("Get categories error ", e.message.toString())
            send(Resource.Error(message = e.message.toString()))
        }
    }
}